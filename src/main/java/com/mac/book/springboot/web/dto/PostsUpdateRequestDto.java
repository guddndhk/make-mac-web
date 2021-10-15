package com.mac.book.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {

    private String title;
    private String content;

    //업데이트
    @Builder
    public PostsUpdateRequestDto(String title, String content) {

        this.title = title;
        this.content = content;
    }

}
