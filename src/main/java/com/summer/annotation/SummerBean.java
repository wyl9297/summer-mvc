package com.summer.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2018/12/12.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SummerBean {

    String value() default "";

}
