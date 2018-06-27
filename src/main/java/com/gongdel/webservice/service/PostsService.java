package com.gongdel.webservice.service;

import com.gongdel.webservice.domain.posts.PostsRepository;
import com.gongdel.webservice.dto.post.PostsMainResponseDto;
import com.gongdel.webservice.dto.post.PostsSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostsService {

    private PostsRepository postsRepository;

    @Transactional
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
}


