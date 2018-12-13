package com.summer.utils;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2018/12/13.
 */
public class SummerBeanFactory {

    private final static Map<String,Object> BEAN_MAP = new ConcurrentHashMap();

    public static Object getBean( String beanId ){
        return BEAN_MAP.get(beanId);
    }

    public static Set<Map.Entry<String, Object>> getBeanEntrySet (){

        return BEAN_MAP.entrySet();
    }

    public static Boolean putBean ( String beanId , Object value ){
        Object put = BEAN_MAP.put(beanId, value);
        return true;
    }

    public static Boolean containsBean( String beanId ){
        return BEAN_MAP.containsKey(beanId);
    }

    public static int beanSize(){
        return BEAN_MAP.size();
    }

}
