package com.gongdel.webservice.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.Stream;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    /**
     * 실제로 밑의 코드는 SpringDataJpa에서 제공하는 기본 메소드만으로 해결할 수 있다.
        굳이  @Query 를 쓴 이유는, SpringDataJpa에서 제공하지 않는 메소드는 위처럼 쿼리로 작성해도 되는것을 보여주기 위함입니다
     */
    @Query("SELECT p" +
                " FROM Posts p" +
                " ORDER BY p.id DESC"
    )
    Stream<Posts> findAllDesc();
}

/**
 규모가 있는 프로젝트에서의 데이터 조회는 FK의 조인, 복잡한 조건등으로 인해 이런 Entity 클래스만으로 처리하기 어려워 조회용 프레임워크를 추가로 사용합니다.
 대표적 예로 querydsl, jooq, MyBatis 등이 있습니다.
 조회는 위 3가지 프레임워크중 하나를 통해 조회하고, 등록/수정/삭제 등은 SpringDataJpa를 통해 진행합니다.
 **/