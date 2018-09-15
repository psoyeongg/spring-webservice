package com.gongdel.webservice.dto.comment;


import com.gongdel.webservice.domain.comment.Comment;
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
public class CommentRequestDto {

    @NotNull
    private Long postId;

    @NotBlank
    private String content;

    @Builder
    public CommentRequestDto(Long postId, String content) {
        this.postId = postId;
        this.content = content;
    }

    public Comment toEntity() {
        return Comment.builder().content(this.content).posts(new Posts(this.postId)).build();
    }
}
