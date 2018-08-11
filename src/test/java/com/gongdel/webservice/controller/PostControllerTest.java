package com.gongdel.webservice.controller;

import com.gongdel.webservice.domain.posts.PostStatus;
import com.gongdel.webservice.domain.posts.Posts;
import com.gongdel.webservice.dto.post.PostsDetailsResponseDto;
import com.gongdel.webservice.dto.post.PostsSaveRequestDto;
import com.gongdel.webservice.service.PostsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PostsService postsService;

    @Test
    public void 특정포스트_검색() throws Exception {
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

    @Test
    public void 포스트_등록_페이지() throws Exception {
        // when
        this.mvc.perform(get("/posts/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("post/new"))
                .andReturn();
    }

   /* // TODO: postman이나 service 테스트할 경우 null 에러가 뜨지 않으나, mockito를 거치면 에러가 존재- 해결 필요
    @Test
    public void 포스트_등록() throws Exception {
       *//* Posts entity = PostsSaveRequestDto.builder()
                .author("gongdel@gmail.com")
                .content("테스트")
                .title("테스트 타이틀")
                .code("마크업")
                .build().toEntity();
        given(postsService.save(any())).willReturn(entity);*//*

        this.mvc.perform(post("/posts/new")
                .param("title", "제목1")
                .param("content", "내용1")
                .param("author", "gongdel")
                .param("code", "마크다운1"))
                .andExpect(status().isFound())
                .andExpect(header().string(HttpHeaders.LOCATION, "/posts/1"));
    }*/

    // 수정 페이지

    /**
     * given() 메서드와 willReturn() 메서드는 가짜 객체들 만들기 위함이다.
     * 예를 들어 given(this.postService.findByIdAndStatus(anyLong(), anyObject())).willReturn(new Post(“제목”, “컨텐츠”,”마크다운”, PostStatus.Y)) 이 코드는
     * postService.findByIdAndStatus 호출하면 return 값으로 new Post(“제목”, “컨텐츠”,”마크다운”, PostStatus.Y)를 받아 올거라는 가짜로 만든 객체다.
     * 실제 postService 메서드는 호출하지 않으며 AOP를 통해 가짜 객체만 넣어서 리턴한다.
     * <p>
     * - http://wonwoo.ml/index.php/post/1159
     */
    @Test
    public void getEditPost() throws Exception {
        // given & when
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .author("gongdel@gmail.com")
                .content("테스트")
                .title("테스트 타이틀")
                .build();
        given(this.postsService.findByPost(anyLong(), anyObject())).willReturn(new PostsDetailsResponseDto(dto.toEntity()));
        MvcResult mvcResult = this.mvc.perform(get("/posts/edit/{id}", 1))
                .andExpect(status().isOk())
                .andReturn();
        PostsDetailsResponseDto postDto = (PostsDetailsResponseDto) mvcResult.getModelAndView().getModel().get("editPost");

        // then
        assertThat(postDto.getTitle()).isEqualTo("테스트 타이틀");
        assertThat(postDto.getContent()).isEqualTo("테스트");
        assertThat(postDto.getAuthor()).isEqualTo("gongdel@gmail.com");

    }

    // 수정
    @Test
    public void modifyPost() throws Exception {
        // give & when
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .author("gongdel@gmail.com")
                .content("테스트")
                .title("테스트 타이틀")
                .build();
        given(this.postsService.updatePost(anyLong(), anyObject())).willReturn(new PostsDetailsResponseDto(dto.toEntity()));

        // then
        this.mvc.perform(post("/posts/{id}/edit", 1)
                .param("author", "gongdel@gmail.com")
                .param("content", "테스트")
                .param("title", "테스트 타이틀"))
                .andExpect(status().isFound())
                /**
                 * 302 Found이 응답 코드는 요청한 리소스의 URI가 일시적으로 변경되었음을 의미합니다. 새롭게 변경된 URI는 나중에 만들어질 수 있습니다. 그러므로, 클라이언트는 향후의 요청도 반드시 동일한 URI로 해야합니다.
                 - 즉 PostController를 살펴면 redirect는 controller와 mapping이 이루어지므로 url 주소가 바뀜.
                 반면 status().isOk() 사용했던 test들은 return "page" 이기 떄문에 url의 변동은 없음
                 */
                .andExpect(header().string(HttpHeaders.LOCATION, "/posts/1"));
    }

    @Test
    public void deletePost() throws Exception {
        // give & when
        doNothing().when(postsService).deletePost(anyLong());

        // then
        this.mvc.perform(post("/posts/{id}/delete", 1))
                .andExpect(status().isFound())
                .andExpect(header().string(HttpHeaders.LOCATION, "/#/"));
    }
}