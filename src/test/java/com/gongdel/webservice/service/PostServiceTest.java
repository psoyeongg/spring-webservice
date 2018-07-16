package com.gongdel.webservice.service;

import com.gongdel.webservice.domain.posts.PostStatus;
import com.gongdel.webservice.domain.posts.Posts;
import com.gongdel.webservice.domain.posts.PostsRepository;
import com.gongdel.webservice.dto.post.PostsDetailsResponseDto;
import com.gongdel.webservice.dto.post.PostsMainResponseDto;
import com.gongdel.webservice.dto.post.PostsSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class) // SpringJUnit4ClassRunner의 alias - https://stackoverflow.com/questions/47446529/what-is-the-difference-between-springjunit4classrunner-and-springrunner
@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostsService postsService;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void Dto데이터가_posts테이블에_저장된다() {
        // given
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .author("gongdel@gmail.com")
                .content("테스트")
                .title("테스트 타이틀")
                .build();

        // when
        postsService.save(dto);

        // then
        Posts posts = postsRepository.findAll().get(0);
        assertThat(posts.getAuthor()).isEqualTo(dto.getAuthor());
        assertThat(posts.getContent()).isEqualTo(dto.getContent());
        assertThat(posts.getStatus()).isEqualTo(PostStatus.Y);
        assertThat(posts.getTitle()).isEqualTo(dto.getTitle());
    }

    @Test
    public void Dto데이터가_조회된다() {
        // when
        List<PostsMainResponseDto> dto = postsService.findAllDesc();

        // then
       for(PostsMainResponseDto testDto: dto) {
           System.out.println(testDto.getId());
           System.out.println(testDto.getAuthor());
           System.out.println(testDto.getModifiedDate());
       }
    }

    @Test
    public void Dto데이터_특정id조회() {
        // given
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .author("gongdel@gmail.com")
                .content("테스트")
                .title("테스트 타이틀")
                .build();
        Long id = postsService.save(dto);

        // when
        PostsDetailsResponseDto testPost =  postsService.findByPost(id, PostStatus.Y);

        // then
        assertThat(testPost.getId()).isEqualTo(id);
        assertThat(testPost.getContent()).isEqualTo("테스트");

    }
}