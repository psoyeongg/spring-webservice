package com.gongdel.webservice.domain;

import com.gongdel.webservice.domain.posts.PostStatus;
import com.gongdel.webservice.domain.posts.Posts;
import com.gongdel.webservice.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostReppsitoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanUp() {
        /*
         * 이후 테스트 코드에 영향을 끼치지 않기 위해, 테스트 메소드가 끝날 때마다 repository를 전체 비우는 코드
         */
        postsRepository.deleteAll();
    }

    @Test
    public void saveTest() {
        // given - 테스트 기반 환경을 구축하는 단계
        postsRepository.save(Posts.builder().title("테스트 게시글").content("테스트 본문").author("yolip51@gmail.com").build());

        //when - 테스트 하고자 하는 행위 선언
        List<Posts> postsList = postsRepository.findAll();

        //then - 테스트 결과 검증
        // 실제로 DB에 insert되었는지 확인하기 위해 조회 후, 입력된 값 확인
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle(), is("테스트 게시글"));
        assertThat(posts.getContent(), is("테스트 본문"));
    }

    @Test
    public void BaseTimeEntity_등록() {
        // given
        LocalDateTime now = LocalDateTime.now();
        postsRepository.save(Posts.builder().title("테스트 게시글").content("테스트 본문").author("yolip51@gmail.com").build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertTrue(posts.getCreatedDate().isAfter(now));
        assertTrue(posts.getModifiedDate().isAfter(now));
    }

    @Test
    public void DetailsResponseDto_세부사항() {
        // given
        Long id = postsRepository.save(Posts.builder().title("테스트 게시글")
                .content("테스트 본문").author("yolip51@gmail.com").status(PostStatus.Y)
                .build()).getId();

        // when
        Posts posts = postsRepository.findByIdAndStatus(id, PostStatus.Y);

        // then
        assertThat(posts.getId(), is(id));
        assertThat(posts.getStatus(), is(PostStatus.Y));
    }
}
