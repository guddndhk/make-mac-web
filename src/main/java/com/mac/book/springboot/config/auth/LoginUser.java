package com.mac.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {

}

/*
@Target(ElementType.PARAMETER)
해당 어노테이션이 생성될 수 있는 위치를 지정한다.
PARAMETER 을 지정 하였으니 메소드의 파마리터로 선언된 객체에서만 사용할 수 있게된다.
@interface
처음보는 클래스이다. 이것을 어노테이션 클래스 라고 부르는것 같다. @interface 즉 이파일을 어노테이션 클래스로 지정한다.
LoginUser 라는 어노테이션이 생긴것.
 */