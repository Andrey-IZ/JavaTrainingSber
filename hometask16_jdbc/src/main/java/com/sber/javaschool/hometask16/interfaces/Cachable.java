package com.sber.javaschool.hometask16.interfaces;

import com.sber.javaschool.hometask16.dao.SourceDao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cachable {
    Class<? extends SourceDao> value();
}
