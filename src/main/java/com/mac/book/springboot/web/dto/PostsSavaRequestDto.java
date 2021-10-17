package com.mac.book.springboot.web.dto;

import com.mac.book.springboot.domain.posts.Posts;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.util.SystemPropertyUtils;

import javax.xml.bind.SchemaOutputResolver;

@Getter
@NoArgsConstructor
public class PostsSavaRequestDto {

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSavaRequestDto(String title, String content, String author) {

        this.title = title;
        this.content = content;
        this.author = author;
    }
    
    public Posts toEntity() {

        return Posts.builder().title(title).content(content).author(author).build();
    }
}

