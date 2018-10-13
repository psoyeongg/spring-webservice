package com.gongdel.webservice.service;

import com.gongdel.webservice.domain.posts.PostStatus;
import com.gongdel.webservice.domain.posts.Posts;
import com.gongdel.webservice.domain.posts.PostsRepository;
import com.gongdel.webservice.dto.category.CategoryRequestDto;
import com.gongdel.webservice.dto.post.PostsDetailsResponseDto;
import com.gongdel.webservice.dto.post.PostsMainResponseDto;
import com.gongdel.webservice.dto.post.PostsSaveRequestDto;
import org.junit.After;
import org.junit.Before;
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
    private CategoryService categoryService;

    @Autowired
    private PostsRepository postsRepository;

 /*   @Before
    public void create_참조하는category() {
        CategoryRequestDto categoryRequestDto =
                CategoryRequestDto.builder()
                        .id(1L)
                        .name("spring")
                .build();

        categoryService.createCategory(categoryRequestDto);
    }*/
    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void Dto데이터가_posts테이블에_저장된다() {
        // given
        CategoryRequestDto categoryRequestDto =
                CategoryRequestDto.builder()
                        .id(1L)
                        .name("spring")
                        .build();

        categoryService.createCategory(categoryRequestDto);
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .author("gongdel@gmail.com")
                .content("테스트")
                .title("테스트 타이틀")
                .code("마크업")
                .categoryId(1L)
                .build();

        // when
       Posts posts = postsService.save(dto);

        // then
        //Posts posts = postsRepository.findAll().get(0);
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
        CategoryRequestDto categoryRequestDto =
                CategoryRequestDto.builder()
                        .id(1L)
                        .name("spring")
                        .build();

        categoryService.createCategory(categoryRequestDto);
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .author("gongdel@gmail.com")
                .content("테스트")
                .title("테스트 타이틀")
                .categoryId(1L)
                .build();
        Posts givenPosts = Posts.builder().title("제목").content("테스트내용").author("gongdel").code("마크다운").status(PostStatus.Y).build();
        Long id = postsService.save(dto).getId();

        // when
        PostsDetailsResponseDto testPost =  postsService.findByPost(id, PostStatus.Y);

        // then
        assertThat(testPost.getId()).isEqualTo(id);
        assertThat(testPost.getContent()).isEqualTo("테스트");

    }

    @Test
    public void Dto데이터_변경() {
        // given
        CategoryRequestDto categoryRequestDto =
                CategoryRequestDto.builder()
                        .id(1L)
                        .name("spring")
                        .build();

        categoryService.createCategory(categoryRequestDto);
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .author("gongdel@gmail.com")
                .content("테스트")
                .title("테스트 타이틀")
                .categoryId(1L)
                .build();
        Long id = postsService.save(dto).getId();
        /*Posts newPosts = new Posts();
        newPosts.setTitle("수정제목");
        newPosts.setContent("수정내용");
        newPosts.setCode("수정코드");
        newPosts.setStatus(PostStatus.Y);*/
        PostsSaveRequestDto updateDto = PostsSaveRequestDto.builder()
                .author("gongdelupdate@gmail.com")
                .content("테스트 update")
                .title("테스트 타이틀 update")
                .build();

        // when
        postsService.updatePost(id, updateDto);

        // then
        Posts posts = postsRepository.findOne(id);
        assertThat(posts.getContent()).isEqualTo(updateDto.getContent());
        assertThat(posts.getStatus()).isEqualTo(PostStatus.Y);
        assertThat(posts.getTitle()).isEqualTo(updateDto.getTitle());
        assertThat(posts.getCode()).isEqualTo(updateDto.getCode());

    }
}