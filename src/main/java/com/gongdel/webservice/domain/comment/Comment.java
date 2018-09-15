package com.gongdel.webservice.domain.comment;

import com.gongdel.webservice.domain.BaseTimeEntity;
import com.gongdel.webservice.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    private LocalDateTime regDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Posts posts;

    @Builder
    public Comment(String content, Posts posts) {
        this.content = content;
        this.posts = posts;
    }
}


