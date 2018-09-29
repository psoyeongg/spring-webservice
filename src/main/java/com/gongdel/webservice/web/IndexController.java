package com.gongdel.webservice.web;

import com.gongdel.webservice.domain.posts.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsRepository postsRepository;

    @GetMapping("/")
    public String home(Model model, Pageable pageable) {
        model.addAttribute("posts", postsRepository.findAll(pageable));

        return "index";
    }
}
