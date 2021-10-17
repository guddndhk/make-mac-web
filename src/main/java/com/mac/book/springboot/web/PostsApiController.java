package com.mac.book.springboot.web;

import com.mac.book.springboot.service.posts.PostsService;
import com.mac.book.springboot.web.dto.PostsResponseDto;
import com.mac.book.springboot.web.dto.PostsSavaRequestDto;
import com.mac.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSavaRequestDto requestDto) {

        System.out.println("400문제!!!!!!!!!!!!!!!");// 단위 테스트를 통과 했기에 컨트롤러에 문제가 없는걸 알지만 혹시나 하는 마음에..
        //역시나 화면딴 js쪽 문제였다
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {

        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id) {

        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {

        postsService.delete(id);

        return id;
    }
}
