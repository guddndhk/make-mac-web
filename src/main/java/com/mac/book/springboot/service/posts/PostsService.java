package com.mac.book.springboot.service.posts;

import com.mac.book.springboot.domain.posts.PostsRepository;
import com.mac.book.springboot.web.dto.PostsSavaRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSavaRequestDto requestDto) {

        return postsRepository.save(requestDto.toEntity()).getId();
    }

}
