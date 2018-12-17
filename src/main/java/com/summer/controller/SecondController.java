package com.summer.controller;

import com.summer.annotation.SummerAutowired;
import com.summer.annotation.SummerController;
import com.summer.annotation.SummerRequestMapping;
import com.summer.annotation.SummerRequestParam;
import com.summer.service.SecondService;
import com.summer.utils.SummerModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/12/17.
 */
@SummerController
@SummerRequestMapping("second")
public class SecondController {

    @SummerAutowired("SeondServiceImpl")
    SecondService secondService;

    @SummerRequestMapping("laugh")
    public SummerModelAndView laugh(HttpServletRequest request , HttpServletResponse response ,@SummerRequestParam("who") String who , @SummerRequestParam("where") String where){
        SummerModelAndView summerModelAndView = new SummerModelAndView("fall");
        summerModelAndView.addObject("value" , secondService.laugh(who,where) );
        return summerModelAndView;
    }

}
