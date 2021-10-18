package com.mac.book.springboot;
import com.mac.book.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)//테스트를 진행시 Junit에 내장된 실행자외 다른 실행자를 실행 여기서는 스프링부트 테스트와 Junit 사이의 연결자 역할
@WebMvcTest(controllers = HelloController.class)//springMVC에 집중할수 있는 @, 선언시 컨트롤러및 컨트롤러 어드바이스 사용가능
public class HelloControllerTest {

    //스프링이 관리하는 Bean 을 주입 받는다.
    @Autowired
    private MockMvc mvc; //웹API 테스트시 사용 ,springMVC 의 시작점, 이클래스를 통해 HTTP GET/POST 등의 API 테스트를 진행 가능하다.

    //hello 리턴 테스트
    @Test
    public void hello() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello")).andExpect(status().isOk()).andExpect(content().string(hello));
        //MockMvc 를 통해 /hello 주소로 HTTP GET 요청 체이닝 지원으로 여러 검증기능을 이어서 선언가능하다
        // .andExpect(status().isOk()) 는 mvc.perform 결과 검증 HTTP Header Status 검증 200,404,500 등의 상태 검증 ok=200
        //.andExpect(content().string(hello)) 는 위와같이 perform 검증 및 응답 본문의 내용 검증 여기서는 hello 값을 검증한다.
    }

    //helloDto 리턴 테스트
    @Test
    public void helloDtoReturn() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto").param("name",name)
                        .param("amount", String.valueOf(amount)))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.name",is(name)))
                        .andExpect(jsonPath("$.amount",is(amount)));
    }


}
