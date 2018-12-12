package com.summer.configration;

import com.summer.annotation.SummerBean;
import com.summer.annotation.SummerConfiguration;
import com.summer.entity.Student;

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


    @SummerBean("view-suffix")
    public String setViewSuffix(){
        return ".jsp";
    }

    @SummerBean("view-prefix")
    public String setPreSuffix(){
        return "../WEB-INF/pages";
    }


}
