package com.gongdel.webservice.dto.post;

import com.gongdel.webservice.domain.posts.PostStatus;
import com.gongdel.webservice.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsDetailsResponseDto extends PostsMainResponseDto{

    private String content;
    private String code;
    private PostStatus status;

    public PostsDetailsResponseDto(Posts entity) {
        super(entity);
        this.content = entity.getContent();
        this.code = entity.getCode();
        this.status = entity.getStatus();
    }
}
