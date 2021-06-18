package com.miex.util.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Id {
    static String STRATEGY_RANDOM = "RANDOM";
    static String STRATEGY_INCR = "INCR";
    static String STRATEGY_SNOWFLAKE = "SNOWFLAKE";

    String value() default "";

    /**
     * id生成策略，
     * @return
     */
    String strategy() default "RANDOM";

}
