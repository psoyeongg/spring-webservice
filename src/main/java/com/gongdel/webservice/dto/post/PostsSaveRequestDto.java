package com.gongdel.webservice.dto.post;

import com.gongdel.webservice.domain.category.Category;
import com.gongdel.webservice.domain.posts.PostStatus;
import com.gongdel.webservice.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class PostsSaveRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String author;
    private String code;

    @NotNull
    private Long categoryId;

    private  String categoryName;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author, String code) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.code = code;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title).content(content)
                .author(author).code(code)
                .status(PostStatus.Y).category(Category.builder().id(categoryId).build())
                .build();
    }
}
