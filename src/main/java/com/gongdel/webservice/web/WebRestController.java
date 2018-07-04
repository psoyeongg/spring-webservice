package com.gongdel.webservice.web;

import com.gongdel.webservice.dto.post.PostsSaveRequestDto;
import com.gongdel.webservice.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@AllArgsConstructor
public class WebRestController {

    /**
     * 의존성 주입하는 방식 3가지
     * @Autowired : 비권장
     * setter
     * 생성자 : 가장 권장
     *
     * 밑의 필드는 생성자 방식인데 생성자가 없는 이유?
     * 위의 @AllArgsConstructor가 역할을 대신해줌
     *
     * 즉 밑과 같은 상황:
     *
        @RestController
        public class WebRestController {

            private PostsRepository postsRepository;

            public WebRestController(PostsRepository postsRepository) {
                this.postsRepository = postsRepository;
            }
    }

     */
    private PostsService postsService;
    private Environment environment;

    @GetMapping("/hello")
    public String hello() {
        return "HelloWorld";
    }

    @PostMapping("/posts")
    public Long savePosts(@RequestBody PostsSaveRequestDto dto) {
        return postsService.save(dto);
    }

    @GetMapping("/profile")
    public String getProfile () {
        return Arrays.stream(environment.getActiveProfiles())
                .findFirst()
                .orElse("");

    }
}
