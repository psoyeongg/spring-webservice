package com.gongdel.webservice.dto.post;

import com.gongdel.webservice.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsModifyRequestDto {

    private String title;
    private String content;
    private String code;

    @Builder
    public PostsModifyRequestDto(String title, String content, String code) {
        this.title = title;
        this.content = content;
        this.code = code;
    }

}
