package com.mac.book.springboot.config.auth;

import com.mac.book.springboot.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver  implements HandlerMethodArgumentResolver{

    private final HttpSession httpSession;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;

        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());

        return isLoginUserAnnotation && isUserClass;
    }

    /*
    supportsParameter(MethodParameter parameter)
    컨트롤러 메소드의 특정 파라미터를 지원하는지 판단한다.
    여기서는 파라미터에 @LoginUser 어노테이션이 붙어있고, 파라미터 클래스 타입이 SessionUser.class인 경우 true 반환
    .getParameterAnnotation(LoginUser.class) :: 같은 패키지 안에 있는 @LoginUser 클래스 참조
     */

    @Override
    public Object resolveArgument( MethodParameter parameter, ModelAndViewContainer mavContainer,
                                   NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception{

        return httpSession.getAttribute("user");
    }

    /*
    resolveArgument
    파마미터에 전달할 객체를 생성 , 해당 메서드는 세션에서 객체를 가져온다.
     */

}
