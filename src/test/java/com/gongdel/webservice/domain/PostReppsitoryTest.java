package com.gongdel.webservice.domain;

import com.gongdel.webservice.domain.posts.Posts;
import com.gongdel.webservice.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostReppsitoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanUp() {
        /**
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
}
