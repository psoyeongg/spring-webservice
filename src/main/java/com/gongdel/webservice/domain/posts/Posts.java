package com.gongdel.webservice.domain.posts;

import com.gongdel.webservice.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
/*
@NoArgsConstructor(access = AccessLevel.PROTECTED)
*/
@Getter
@Setter
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    // https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/lob-annotation.html
    @Lob
    private String code;

    @Enumerated(EnumType.STRING)
    private PostStatus status;

    // 등록
    @Builder
    public Posts(String title, String content, String author, PostStatus status) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.status = status;
    }

}
