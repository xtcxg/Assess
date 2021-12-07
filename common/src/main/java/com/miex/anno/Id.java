package com.miex.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Id {
    /**
     * UUID，String
     */
    static String STRATEGY_RANDOM = "RANDOM";
    /**
     * auto incr,Long
     */
    static String STRATEGY_INCR = "INCR";
    /**
     * snowflake,Long
     */
    static String STRATEGY_SNOWFLAKE = "SNOWFLAKE";

    String value() default "";

    /**
     * id生成策略，
     * @return
     */
    String strategy() default "RANDOM";

}
