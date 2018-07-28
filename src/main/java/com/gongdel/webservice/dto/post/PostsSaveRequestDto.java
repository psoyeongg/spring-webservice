package com.gongdel.webservice.dto.post;

import com.gongdel.webservice.domain.posts.PostStatus;
import com.gongdel.webservice.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;
    private String code;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author, String code) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.code = code;
    }

    public Posts toEntity() {
        return Posts.builder().title(title).content(content).author(author).code(code).status(PostStatus.Y).build();
    }
}
