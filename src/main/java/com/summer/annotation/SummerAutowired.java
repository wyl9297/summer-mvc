package com.summer.annotation;

import java.lang.annotation.*;

/**
 * The interface Summer autowired.
 *
 * @author :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
 * @date :2018-07-27 10:28:25
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SummerAutowired {

    /**
     * Value string.
     *
     * @return the string
     * @author :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
     * @date :2018-07-27 10:28:25
     */
    String value() default "";

}
