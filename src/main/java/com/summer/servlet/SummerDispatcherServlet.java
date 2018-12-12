package com.summer.servlet;


import com.summer.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yanlinwang
 */
public class SummerDispatcherServlet extends HttpServlet {


    List<String> packageNames = new LinkedList<>();

    private final Map<String, Object> instanceMap = new ConcurrentHashMap();
    private final Map<String, Object> handerMap = new ConcurrentHashMap();

    @Override
    public void init(ServletConfig config) {
        //通过web.xml里面配置的路径获取要扫描的路径
        String scanPackage = config.getInitParameter("scanPackage");

        // 包扫描,获取包中的文件
        scanPackage(scanPackage);

        //创建实例
        try {
            filterAndInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //创建映射关系
        handerMap();

        //根据@SummerAutowired注解 注入实例
        ioc();
    }

    private void scanPackage(String Package) {
        //根据包名获取包的全路径
        URL url = this.getClass().getClassLoader().getResource("/" + Package.replaceAll("\\.", "/"));
        String pathFile = url.getFile();
        File file = new File(pathFile);
        String[] fileList = file.list();
        for( String path : fileList ){
            File eachFile = new File(pathFile + path);
            if( eachFile.isDirectory() ){
                scanPackage(Package + "." + eachFile.getName());
            } else {
                packageNames.add(Package + "." + eachFile.getName());
            }
        }
    }

    private void filterAndInstance() throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        //scanPackage 执行完毕后 所有类的全路径已经存在 packageNames 这个List当中
        if( packageNames.size() <= 0 ){
            return;
        }
        for( String className : packageNames ){
            //通过反射获取到类的class对象
            Class<?> clazz = Class.forName(className.replace(".class", "").trim());
            if( clazz.isAnnotationPresent(SummerController.class) ){
                Object instance = clazz.newInstance();
                SummerRequestMapping requestMapping = clazz.getAnnotation(SummerRequestMapping.class);
                String key = requestMapping.value();
                instanceMap.put(key,instance);
            } else if ( clazz.isAnnotationPresent(SummerService.class) ) {
                Object instance = clazz.newInstance();
                SummerService service = clazz.getAnnotation(SummerService.class);
                String key = service.value();
                instanceMap.put(key,instance);
            } else if ( clazz.isAnnotationPresent( SummerConfiguration.class ) ){
                Object instance = clazz.newInstance();
                Method[] methods = instance.getClass().getMethods();
                for (Method method : methods){
                    method.setAccessible(true);
                    if( method.isAnnotationPresent(SummerBean.class) ){
                        String value = method.getAnnotation(SummerBean.class).value();
                        Object result = method.invoke(instance);
                        instanceMap.put(value,result);
                    }
                }

            } else {
                continue;
            }
        }
    }

    private void handerMap() {

        if( instanceMap.size() <= 0 ){
            return;
        }
        for (Map.Entry<String, Object> entry : instanceMap.entrySet()){
            if( entry.getValue().getClass().isAnnotationPresent(SummerController.class) ){
                SummerRequestMapping requestMapping = entry.getValue().getClass().getAnnotation(SummerRequestMapping.class);
                String ctvalue = requestMapping.value();
                Method[] methods = entry.getValue().getClass().getMethods();
                for (Method method : methods){
                    if( method.isAnnotationPresent(SummerRequestMapping.class) ){
                        SummerRequestMapping rm = method.getAnnotation(SummerRequestMapping.class);
                        String rmvalue = rm.value();
                        handerMap.put("/" + ctvalue + "/" + rmvalue, method);
                    } else {
                        continue;
                    }
                }
            } else {
                continue;
            }
        }

    }

    private void ioc() {
        if( instanceMap.size() <= 0 ){
            return;
        }

        for( Map.Entry<String, Object> entry : instanceMap.entrySet() ){
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for( Field field : fields ){
                field.setAccessible(true);
                if( field.isAnnotationPresent(SummerAutowired.class) ){
                    String value = field.getAnnotation(SummerAutowired.class).value();
                    Object instance = instanceMap.get(value);
                    field.setAccessible(true);
                    try {
                        field.set(entry.getValue(),instance);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    private Object[] handleParam(Method method , HttpServletRequest request ,HttpServletResponse response) throws IllegalAccessException, InstantiationException {
        Parameter[] parameters = method.getParameters();
        Object[] param = new Object[parameters.length];
        int i = 2;
        param[0] = request;
        param[1] = response;
        for ( Parameter parameter : parameters ){
            if ( parameter.isAnnotationPresent(SummerRequestParam.class) ) {
                SummerRequestParam requestParam = parameter.getAnnotation(SummerRequestParam.class);
                String value = requestParam.value();
                if( !"".equals(value) && value != null ){
                    param[i++] = request.getParameter(value);
                }
            }
        }
        return param;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();
        String context = request.getContextPath();
        String path = url.replace(context, "");
        Method method = (Method) handerMap.get(path.split("\\.")[0]);
        Object controller = instanceMap.get(path.split("/")[1]);
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
            Object[] objects = handleParam(method, request, response);
            Object invoke = method.invoke(controller, objects);
            if( invoke instanceof SummerModelAndView ){
                doViewResolve(invoke,request,response);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    private void doViewResolve(Object object, HttpServletRequest request, HttpServletResponse response) {
        SummerModelAndView summerModelAndView;
        String redirectPrefix = "redirect:";
        String viewSuffix = "view-suffix";
        String viewprefix = "view-prefix";

        if( ! (object instanceof SummerModelAndView) ){
            return;
        }
        summerModelAndView = (SummerModelAndView) object;
        Map maps = summerModelAndView.getObjects();
        Iterator iterator = maps.keySet().iterator();
        while( iterator.hasNext() ){
            String next = (String)iterator.next();
            request.setAttribute(next,maps.get(next));
        }
        String viewName = summerModelAndView.getViewName();
        if( viewName.startsWith(redirectPrefix) ){
            String url =  instanceMap.get(viewprefix)+ "/" + viewName.split(":")[1] + instanceMap.get(viewSuffix);
            try {
                response.sendRedirect( url );
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            String url =  instanceMap.get(viewprefix)+ "/" + viewName + instanceMap.get(viewSuffix);
            try {
                request.getRequestDispatcher( url ).forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
