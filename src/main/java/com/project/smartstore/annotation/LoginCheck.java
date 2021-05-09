package com.project.smartstore.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 로그인 체크를 위한 어노테이션.
 *
 * @Retention
 *      : 어노테이션 유형이있는 어노테이션이 보존되는 기간을 나타낸다.
 *      어노테이션 유형 선언에 Retention 어노테이션이없는 경우 기본값은 RetentionPolicy.CLASS이다.
 * @Target
 *      : 어노테이션 선언할 타입을 지정한다.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LoginCheck {
}
