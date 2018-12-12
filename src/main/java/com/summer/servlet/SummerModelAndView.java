package com.summer.servlet;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/12/12.
 */
public class SummerModelAndView {

    private final Map<String,Object> attributes = new HashMap<>();

    private String viewName ;

    public SummerModelAndView(String viewName) {
        this.viewName = viewName;
    }

    public void addObject(String key , Object value){
        attributes.put(key,value);
    }

    public Map getObjects(){
        return attributes;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
}