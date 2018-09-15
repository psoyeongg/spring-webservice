package com.gongdel.webservice.service;


import com.gongdel.webservice.domain.comment.Comment;
import com.gongdel.webservice.domain.comment.CommentRepository;
import com.gongdel.webservice.dto.comment.CommentRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment createComment(CommentRequestDto commentRequestDto) {
        return commentRepository.save(commentRequestDto.toEntity());
    }

    public void deleteComment(Long id) {
        commentRepository.delete(id);
    }
}
