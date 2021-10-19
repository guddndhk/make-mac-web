package com.mac.book.springboot.config.auth;

import com.mac.book.springboot.config.auth.dto.OAuthAttributes;
import com.mac.book.springboot.config.auth.dto.SessionUser;
import com.mac.book.springboot.domain.user.User;
import com.mac.book.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User>
                delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest
                .getClientRegistration()
                .getRegistrationId();
        String userNameAttributeName = userRequest
                .getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        /*
        getRegistrationId()
        현재 로그인 진행 중인 서비스를 구분하는 코드
        현재 구글 로그인 API 만 사용하여 불필요한 값 그러나 이후 네이버 로그인 등 타 API 연동시에 구글인지 네이버인지 구분할 수 있다.
        .getUserNameAttributeName()
        OAuth2 로그인 진행 시 키가 되는 필드 값 을 이야기함 = PK 로 이해 하면된다
        구글은 기본 코드를 지원 하나 ,네이버 카카오 등은 기본지원이 없다. 구글의 기본코드는 sub
        후에 네이버 로그인 구글 로그인 동시 지원할 때 사용

         */

        OAuthAttributes attributes = OAuthAttributes
                .of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        /*
        OAuthAttributes
        OAuth2UserService 를 통해 가져온 OAuth2User의 attribute 를 담을 클래스 다른 소셜 로그인도 해당 클래스 사용
         */

        User user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user));

        /*
        SessionUser
        세션에 사용자 정보를 저장하기 위한 Dto 클래스
         */

        return new DefaultOAuth2User(Collections.singleton(new
                SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes) {

        User user = userRepository.findByEmail(attributes
                        .getEmail())
                .map(entity -> entity.update(attributes.getName(),
                        attributes.getPicture())).orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}
