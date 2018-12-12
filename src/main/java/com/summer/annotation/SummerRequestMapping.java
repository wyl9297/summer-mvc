package com.summer.annotation;

import java.lang.annotation.*;

/**
 * The interface Summer request mapping.
 *
 * @author :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
 * @date :2018-07-27 10:28:35
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SummerRequestMapping {

    /**
     * Value string.
     *
     * @return the string
     * @author :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
     * @date :2018-07-27 10:28:35
     */
    String value() default "";

}
