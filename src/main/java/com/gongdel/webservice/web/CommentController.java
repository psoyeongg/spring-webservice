package com.gongdel.webservice.web;

import com.gongdel.webservice.dto.comment.CommentRequestDto;
import com.gongdel.webservice.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public String createComment(@ModelAttribute("commentDto" ) @Valid CommentRequestDto commentRequestDto
                                , BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "post/post";
        }

        model.addAttribute("comment", commentService.createComment(commentRequestDto));
        return "redirect:/posts/" + commentRequestDto.getPostId();
    }

    @PostMapping("/{postId}/{commentId}")
    public String deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
        commentService.deleteComment(commentId);

        return "redirect:/posts/" + postId;
    }
}
