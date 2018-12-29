package com.summer.controller;

import com.summer.annotation.SummerAutowired;
import com.summer.annotation.SummerController;
import com.summer.annotation.SummerRequestMapping;
import com.summer.annotation.SummerRequestParam;
import com.summer.utils.SummerModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@SummerController
@SummerRequestMapping("nettyChat")
public class NettyChatController {

    @SummerAutowired("allUser")
    List allUser;


    @SummerRequestMapping("selectChatType")
    public SummerModelAndView selectChatType(HttpServletRequest request, HttpServletResponse response){
        SummerModelAndView modelAndView = new SummerModelAndView("netty/selectChatType");
        List list = new ArrayList<>();
        list.addAll(allUser);
        modelAndView.addObject("allUser",list);
        return modelAndView;
    }

    @SummerRequestMapping("goChat")
    public SummerModelAndView goChat(HttpServletRequest request, HttpServletResponse response, @SummerRequestParam("chatType") String chatType, @SummerRequestParam("userRole") String userRole){
        SummerModelAndView modelAndView = new SummerModelAndView(null);
        if( "chatRoom".equals(chatType) ){
            modelAndView = toChatRoom(request,response);
        } else if( "oneToOne".equals(chatType) ){
            modelAndView = pointToPoint(request,response);
        }
        modelAndView.addObject("userName",userRole);
        return modelAndView;
    }

    private SummerModelAndView toChatRoom(HttpServletRequest request, HttpServletResponse response){
        String userName = request.getParameter("userName");
        SummerModelAndView modelAndView = new SummerModelAndView("netty/chatRoom");
        modelAndView.addObject("userName",userName);
        return modelAndView;
    }

    private SummerModelAndView pointToPoint(HttpServletRequest request, HttpServletResponse response){
        String userName = request.getParameter("userName");
        SummerModelAndView modelAndView = new SummerModelAndView("netty/pointToPoint");
        modelAndView.addObject("userName",userName);
        List list = new ArrayList<>();
        list.addAll(allUser);
        modelAndView.addObject("allUser",list);
        return modelAndView;
    }

}