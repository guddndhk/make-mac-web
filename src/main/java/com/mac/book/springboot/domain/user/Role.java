package com.mac.book.springboot.domain.user;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST","손님"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;


    //스프링 시큐리티에서는 권한 코드에 항상 ROLE_ 이 앞에 있어야한다 그래서 GUEST,USER 앞에 ROLE_ 을 붙여주었다.

}
