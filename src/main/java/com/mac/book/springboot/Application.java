package com.mac.book.springboot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // jpa Auditing 활성화
@SpringBootApplication
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }

    /*
    SpringBootApplication 로 인해 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정된다
    @SpringBootApplication 이 있는 위치부터 설정을 읽어간다 때문에 이 클래스는 항상 프로젝트의 최상단에 위치하여야 한다
    메인 메소드에서 실행하는 SpringApplication.run으로 인해 내장 WAS 를 실행한다
    내장 WAS 를 사용하면 언제 어디서나 같은 환경에서 스프링 부트를 배포 할 수 있다.
    외장 WAS 를 썻을때를 생각해보면 WAS의 종류,버전,설정을 (환경) 모두 일치 시켜야했다 내장 WAS 쓰면 이와 같은 문제들을 간단히 해결할 수 있다
    유지보수가 매우 편해짐
     */
}
