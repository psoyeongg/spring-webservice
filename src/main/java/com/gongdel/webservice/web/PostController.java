package com.gongdel.webservice.web;

import com.gongdel.webservice.domain.posts.PostStatus;
import com.gongdel.webservice.dto.post.PostsDetailsResponseDto;
import com.gongdel.webservice.exception.NotFoundException;
import com.gongdel.webservice.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostsService postsService;

    @GetMapping("/{id}")
    public String findByPost(@PathVariable Long id, Model model) {
        PostsDetailsResponseDto post = postsService.findByPost(id, PostStatus.Y);
        if (post == null) {
            throw new NotFoundException("Not Found - " + id);
        }

    }

}
