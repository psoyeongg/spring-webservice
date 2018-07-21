package com.gongdel.webservice.service;

import com.gongdel.webservice.domain.posts.PostStatus;
import com.gongdel.webservice.domain.posts.Posts;
import com.gongdel.webservice.domain.posts.PostsRepository;
import com.gongdel.webservice.dto.post.PostsDetailsResponseDto;
import com.gongdel.webservice.dto.post.PostsMainResponseDto;
import com.gongdel.webservice.dto.post.PostsSaveRequestDto;
import com.gongdel.webservice.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class PostsService {

    private PostsRepository postsRepository;


    public Long save(PostsSaveRequestDto dto) {
        return postsRepository.save(dto.toEntity()).getId();
    }

    /**
     * @Transactional(readOnly = true) :
     * 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선되기 때문에 특별히 등록/수정/삭제 기능이 없는 메소드에선 사용하시는걸 추천.
     */
    @Transactional(readOnly = true)
    public List<PostsMainResponseDto> findAllDesc() {
        return postsRepository.findAllDesc()
                .map(PostsMainResponseDto::new) // a:b - a클래스에 있는 b를 생성 = https://stackoverflow.com/questions/27015495/meaning-of-in-java-syntax
                                                // == .map(posts -> new PostMainResponseDto(posts))
                .collect(Collectors.toList());
    }

    // 글 세부 정보 확인
    @Transactional(readOnly = true)
    public PostsDetailsResponseDto findByPost(Long id, PostStatus status) {
        Posts post = postsRepository.findByIdAndStatus(id, status);
        if (post == null) {
            throw new NotFoundException("Not Found Id - " + id);
        }

        PostsDetailsResponseDto responseDto = new PostsDetailsResponseDto(post);
        return responseDto;
    }

    // 글 업데이트 하기
    public PostsDetailsResponseDto updatePost(Long id, Posts posts) {
        Posts oldPosts = postsRepository.findByIdAndStatus(id, PostStatus.Y);
        if (oldPosts == null) {
            throw new NotFoundException("Not Found - " + id);
        }

        oldPosts.setContent(posts.getContent());
        oldPosts.setCode(posts.getCode());
        oldPosts.setTitle(posts.getTitle());

        PostsDetailsResponseDto newPosts = new PostsDetailsResponseDto(oldPosts);

        return newPosts;
    }
}


