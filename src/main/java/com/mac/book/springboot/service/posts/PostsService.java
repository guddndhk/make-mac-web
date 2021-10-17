package com.mac.book.springboot.service.posts;

import com.mac.book.springboot.domain.posts.Posts;
import com.mac.book.springboot.domain.posts.PostsRepository;
import com.mac.book.springboot.web.dto.PostsListResponseDto;
import com.mac.book.springboot.web.dto.PostsResponseDto;
import com.mac.book.springboot.web.dto.PostsSavaRequestDto;
import com.mac.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    //조회
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {

        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) //.map(Posts -> new PostsListResponseDto(posts)) 의 코드도 가능
                .collect(Collectors.toList());
    //postsRepository 결과로 넘어온 Posts의 Stream을 map을 통해 PostsListsResponseDto 변환 > List 로 반환
    }

    //삭제
    @Transactional
    public void delete( Long id) {

        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        postsRepository.delete(posts);
    }
}
