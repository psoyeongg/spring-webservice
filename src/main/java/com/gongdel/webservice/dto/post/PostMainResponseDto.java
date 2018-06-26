package com.gongdel.webservice.dto.post;

import com.gongdel.webservice.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Getter
public class PostMainResponseDto {

    private Long id;
    private String title;
    private String author;
    private String modifiedDate;

    public PostMainResponseDto(Posts entity) {
        this.id = entity.getId();
        this title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = toStringDateTime(entity.getModifiedDate());
    }

    /**
     *  Java 8
     *  View 영역에선 LocalDateTime 타입을 모르기 떄문에, 인식할 수 있는 String 타입의 날짜형식으로 변경
     */
    private String toStringDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Optional : https://medium.com/@joongwon/optional을-이용하여-java의-nullpointerexception을-피해보자-e9cac719a2d6
        return Optional.ofNullable(localDateTime).map(formatter::format).orElse("");
    }
}
