package com.summer.annotation;

import java.lang.annotation.*;

/**
 * The interface Summer controller.
 *
 * @author :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
 * @date :2018-07-27 10:28:31
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SummerController {

    /**
     * Value string.
     *
     * @return the string
     * @author :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
     * @date :2018-07-27 10:28:31
     */
    String value() default "";

}
