package com.summer.configration;

import com.summer.annotation.SummerBean;
import com.summer.annotation.SummerConfiguration;
import com.summer.entity.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/12/12.
 */
@SummerConfiguration
public class TestConfig {

    @SummerBean("student")
    public Student setStudent(){
        Student student = new Student();
        student.setName("炎林王");
        student.setAge(26);
        student.setAddress("北京");
        student.setCompany("必联");
        return student;

    }

    @SummerBean("allUser")
    public List allUser(){
        List<String> list = new ArrayList<>();
        list.add("小学生");
        list.add("初中生");
        list.add("高中生");
        list.add("大学生");
        list.add("硕士");
        list.add("博士");
        list.add("博士后");
        list.add("院士");
        list.add("文盲");
        return list;
    }


    @SummerBean("view-suffix")
    public String setViewSuffix(){
        return ".jsp";
    }

    @SummerBean("view-prefix")
    public String setPreSuffix(){
        return "../WEB-INF/pages";
    }


}
