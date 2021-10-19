package com.mac.book.springboot.config.auth;

import com.mac.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity//Spring Secutiry 설정들을 활성화 시킨다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "h2-console/**")
                .permitAll().antMatchers("/api/v1/**").hasRole(Role.USER.name()).anyRequest()
                .authenticated()
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                .oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);

                /*
                 .csrf().disable().headers().frameOptions().disable()
                  h2-consol 화면을 사용하기 위해 해당 옵션들을 disable 한다.

                 .authorizeRequests()
                  URL별 권한 관리를 설정하는 옵션의 시작점,선언 되어야지만 antMatchers 의 옵션을 사용가능하다.

                 .antMatchers
                  권한 관리 대상을 지정하는 옵션, URL,HTTP 메소드별로 관리가 가능하다
                  "/"등 지정된 URL 들은 permitAll() 옵션을 통해 전체 열람 권한을 주었다.
                  "/api/v1/**" 주소를 가진 API는 USER 권한을 가진 사람만 가능하도록 하였음

                  .anyRequest()
                  설정된 값들 이외 나머지 URL 들을 나타낸다
                  여기서는 authrnticated() 을 추가하여 나머지 URL들은 모두 인증이된 사용자들에게만 허용가능 하도록한다
                  인증된 사용자 = 로그인된 사용자

                  .logout().logoutSuccessUrl("/")
                   로그아웃 기능에 대한 여러 설정의 진입점
                   로그아웃 성공시 / 주소로 이동

                   .oauth2Login()
                   OAuth2 로그인 기능에 대한 여러 설정의 진입점

                   .userInfoEndpoint()
                   OAuth2 로그인 이후 사용자 정보를 가져올 때의 설정들을 담당!

                   .userService
                   소셜 로그인 성공시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
                   리소스 서버(소셜 서비스)에서 사용자정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시 가능하다.
                 */

    }
}
