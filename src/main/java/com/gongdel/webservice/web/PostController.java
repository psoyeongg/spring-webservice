package com.gongdel.webservice.web;

import com.gongdel.webservice.domain.posts.PostStatus;
import com.gongdel.webservice.domain.posts.Posts;
import com.gongdel.webservice.dto.post.PostsDetailsResponseDto;
import com.gongdel.webservice.dto.post.PostsSaveRequestDto;
import com.gongdel.webservice.exception.NotFoundException;
import com.gongdel.webservice.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostsService postsService;

    // 등록 페이지
    @GetMapping("/new")
    public String newPostGet(PostsSaveRequestDto postsSaveRequestDto) {
        return "post/new";
    }

    @PostMapping("/new")
    public String newPostPost(@ModelAttribute("postDto") @Valid PostsSaveRequestDto postsSaveRequestDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "post/new";
        }

        Posts posts = postsService.save(postsSaveRequestDto);
        model.addAttribute("post", posts);

        return "redirect:/posts/" + posts.getId();
    }

    // 특정 posts 검색
    @GetMapping("/{id}")
    public String findByPost(@PathVariable Long id, Model model) {
        PostsDetailsResponseDto post = postsService.findByPost(id, PostStatus.Y);
        if (post == null) {
            throw new NotFoundException("Not Found - " + id);
        }

        model.addAttribute("post", post);
        return "post/post";
    }

    @GetMapping("/edit/{id}")
    public String getEditPost(@PathVariable Long id, Model model) {
        PostsDetailsResponseDto post = postsService.findByPost(id, PostStatus.Y);
        if (post == null) {
            throw new NotFoundException("Not Found - " + id);
        }

        model.addAttribute("editPost", post);
        return "post/edit";
    }
}
