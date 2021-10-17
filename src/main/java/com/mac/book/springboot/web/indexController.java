package com.mac.book.springboot.web;

import com.mac.book.springboot.service.posts.PostsService;
import com.mac.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class indexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {

        //postsService.findAllDesc() 로 가져온 결과를 posts 로 index.mustache 에 전달
        model.addAttribute("posts",postsService.findAllDesc());

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
