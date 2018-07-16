package com.gongdel.webservice.dto.post;

import com.gongdel.webservice.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsDetailsResponseDto extends PostsMainResponseDto{

    private String content;

    public PostsDetailsResponseDto(Posts entity) {
        super(entity);
        this.content = entity.getContent();
    }
}
