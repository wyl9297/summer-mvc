package com.summer.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2018/7/26.
 *
 * @author :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
 * @date :2018-07-27 10:28:42
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SummerRequestParam {

    /**
     * Value string.
     *
     * @return the string
     * @author :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
     * @date :2018-07-27 10:28:42
     */
    String value() default "";

}
