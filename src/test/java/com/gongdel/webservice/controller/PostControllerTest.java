package com.gongdel.webservice.controller;

import com.gongdel.webservice.domain.posts.PostStatus;
import com.gongdel.webservice.domain.posts.Posts;
import com.gongdel.webservice.dto.post.PostsDetailsResponseDto;
import com.gongdel.webservice.service.PostsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    private MockMvc mvc;



    @MockBean
    private PostsService postsService;

    @Test
    public void findByPost() throws Exception {
        // given
        Posts givenPosts = Posts.builder().title("제목").content("테스트내용").author("gongdel").code("마크다운").status(PostStatus.Y).build();
        given(this.postsService.findByPost(anyLong(), anyObject()))
                .willReturn(new PostsDetailsResponseDto(givenPosts));

        // when
        MvcResult mvcResult = this.mvc.perform(get("/posts/{id}", 1))
                .andExpect(status().isOk())
                .andReturn();

        // then
        PostsDetailsResponseDto posts = (PostsDetailsResponseDto) mvcResult.getModelAndView().getModel().get("post");
        assertThat(posts.getTitle()).isEqualTo("제목");
        assertThat(posts.getContent()).isEqualTo("테스트내용");
        assertThat(posts.getCode()).isEqualTo("마크다운");
        assertThat(posts.getStatus()).isEqualTo((PostStatus.Y));
    }


}