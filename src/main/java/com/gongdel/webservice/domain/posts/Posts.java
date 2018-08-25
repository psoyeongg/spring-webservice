package com.gongdel.webservice.domain.posts;

import com.gongdel.webservice.domain.BaseTimeEntity;
import com.gongdel.webservice.domain.category.Category;
import com.gongdel.webservice.domain.comment.Comment;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @OneToMany(mappedBy = "posts", fetch = FetchType.LAZY)
    private List<Comment> comments;

    // 등록
    @Builder
    public Posts(String title, String content, String author, String code, PostStatus status) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.code = code;
        this.status = status;
    }

}
