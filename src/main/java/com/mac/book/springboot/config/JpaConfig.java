package com.mac.book.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing//Application class 에서 해당 클래스로 이동해옴 단위 테스트로 인해....
public class JpaConfig {
}
