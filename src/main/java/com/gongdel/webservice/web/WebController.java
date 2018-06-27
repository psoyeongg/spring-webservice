package com.gongdel.webservice.web;

import com.gongdel.webservice.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class WebController {

    private PostsService postsService;

    /**
     *   handlebars-spring-boot-starter 덕분에 컨트롤러에서 문자열을 반호나할 때 앞의 path와 뒤의 파일 확장자는 자동으로 지정된다.
     *   (prefix: src/main/resources/template, suffix: .hbs)
     */
    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());

        return "main";
    }
}
