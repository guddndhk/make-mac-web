package com.mac.book.springboot.service.posts;

import com.mac.book.springboot.domain.posts.Posts;
import com.mac.book.springboot.domain.posts.PostsRepository;
import com.mac.book.springboot.web.dto.PostsResponseDto;
import com.mac.book.springboot.web.dto.PostsSavaRequestDto;
import com.mac.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    //포스트
    @Transactional
    public Long save(PostsSavaRequestDto requestDto) {

        return postsRepository.save(requestDto.toEntity()).getId();
    }

    //업데이트
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {

        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    //조회
    public PostsResponseDto findById (Long id) {

        Posts entity = postsRepository.findById(id).orElseThrow(() ->
            new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        return new PostsResponseDto(entity);
    }
}
