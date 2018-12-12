package com.summer.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2018/7/26.
 *
 * @author :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
 * @date :2018-07-27 10:28:45
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SummerService {

    /**
     * Value string.
     *
     * @return the string
     * @author :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
     * @date :2018-07-27 10:28:46
     */
    String value() default "";

}
