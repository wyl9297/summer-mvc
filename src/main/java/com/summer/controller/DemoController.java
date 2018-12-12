package com.summer.controller;

import com.summer.annotation.SummerAutowired;
import com.summer.annotation.SummerController;
import com.summer.annotation.SummerRequestMapping;
import com.summer.annotation.SummerRequestParam;
import com.summer.entity.Student;
import com.summer.service.DemoService;
import com.summer.service.OrderService;
import com.summer.servlet.SummerModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2018/7/26.
 *
 * @author :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
 * @date :2018-07-27 10:28:50
 */
@SummerController
@SummerRequestMapping("demo")
public class DemoController {

    /**
     * The Demo service.
     *
     * @author :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
     * @date :2018-07-27 10:28:50
     */
    @SummerAutowired("DemoServiceImpl")
    private DemoService demoService;

    @SummerAutowired("OrderServiceImpl")
    private OrderService orderService;

    @SummerAutowired("student")
    private Student student;

    /**
     * Query.
     *
     * @param request  the request
     * @param response the response
     * @param name     the name
     * @param age      the age
     * @author :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
     * @date :2018-07-27 10:28:50
     */
    @SummerRequestMapping("query")
    public void query(HttpServletRequest request , HttpServletResponse response , @SummerRequestParam("name") String name , @SummerRequestParam("age") String age){
        String query = demoService.query(name, age);
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(query);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete.
     *
     * @param request  the request
     * @param response the response
     * @param name     the name
     * @param age      the age
     * @author :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
     * @date :2018-07-27 10:28:50
     */
    @SummerRequestMapping("delete")
    public void delete(HttpServletRequest request , HttpServletResponse response , @SummerRequestParam("name")String name , @SummerRequestParam("age")String age){
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(demoService.delete(name,age));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * payOrder.
     *
     * @param request  the request
     * @param response the response
     * @param name     the name
     * @param price      the age
     * @author :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
     * @date :2018-07-27 10:28:50
     */
    @SummerRequestMapping("payOrder")
    public void payOrder(HttpServletRequest request , HttpServletResponse response , @SummerRequestParam("name")String name , @SummerRequestParam("price")String price){
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(orderService.payOrder(name,price));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SummerRequestMapping("getConfigStudent")
    public SummerModelAndView getConfigStudent(HttpServletRequest request , HttpServletResponse response,@SummerRequestParam("name")String name ) throws ServletException, IOException {
        System.out.println(student.getName() + " + " + student.getAge() + "+" +  student.getAddress());
        SummerModelAndView summerModelAndView = new SummerModelAndView("winter");
        summerModelAndView.addObject("name",student.getName() + "---" + name);
        summerModelAndView.addObject("age",student.getAge());
        summerModelAndView.addObject("address",student.getAddress());
        summerModelAndView.addObject("company",student.getCompany());
       return summerModelAndView;
    }

}
