package com.gongdel.webservice.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
// @Target : custom Anootation - 어노테이션이 적용할 위치를 결정합니다.
@Target(ElementType.TYPE)   /** (ElementType.TYPE) - Class, interface (including annotation type), or enum declaration */
@Retention(RetentionPolicy.RUNTIME) // 어노테이션의 범위(?)라고 할 수 있겠습니다. 어떤 시점까지 어노테이션이 영향을 미치는지 결정합니다.
public @interface Navigation {

    Selection value();
}

// https://jdm.kr/blog/216
