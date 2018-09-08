package com.gongdel.webservice.domain.category;

import com.gongdel.webservice.domain.BaseTimeEntity;
import com.gongdel.webservice.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Posts> post = new ArrayList<>();

    @Builder
    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}

