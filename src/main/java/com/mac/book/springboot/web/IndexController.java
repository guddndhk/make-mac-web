package com.mac.book.springboot.web;

import com.mac.book.springboot.config.auth.LoginUser;
import com.mac.book.springboot.config.auth.dto.SessionUser;
import com.mac.book.springboot.service.posts.PostsService;
import com.mac.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    //private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        //@ 기반으로 구축 하였기에 모든 컨트롤러는 @LoginUser 만 사용하면 세션 정보를 가져올 수 있다.

        //postsService.findAllDesc() 로 가져온 결과를 posts 로 index.mustache 에 전달
        model.addAttribute("posts", postsService.findAllDesc());

        //CumtomOauth2UserService 에서 로그인 성공 시 세션에 SessionUser 를 저장한다.
        //로그인 성공시 httpSession.getAttribute("user") 즉 세션에서 유저 값을 가져온다.
        //허나 index메소드 외에 다른 컨트롤러 혹은 메소드에서 세션값이 필요할떄 마다 직접 세션에서 값을 가져와야한다 코드의반복
        //어떻게 해야하나 메소드를 인자로 세션값을 바로 받아 쓰기 = 어노테이션기반 작업.

        //@LoginUser 클래스가 있으니 세션 유저는 필요가 없다 어노테이션 기반으로 재설계
        //SessionUser user = (SessionUser) httpSession.getAttribute("user");

        //세션에 저장된 값이 있을때만 model에 userName 을 등록, 세션 저장 값이 없으면 model 에 값이 없으니 로그인 버튼이 보이게 된다.
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {

        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }


    //글 등록
    @GetMapping("/posts/save")
    public String postsSave() {

        return "posts-save";
    }
}
