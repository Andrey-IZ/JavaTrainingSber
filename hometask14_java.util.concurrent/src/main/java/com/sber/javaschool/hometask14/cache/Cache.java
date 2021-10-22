package com.sber.javaschool.hometask14.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cache {
    CacheType cacheType();

    String name() default "temp.cache";

    long limit() default -1L;

    boolean zip() default false;
}
