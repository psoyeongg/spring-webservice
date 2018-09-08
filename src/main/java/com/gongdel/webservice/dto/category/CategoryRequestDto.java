package com.gongdel.webservice.dto.category;

import com.gongdel.webservice.domain.category.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class CategoryRequestDto {

    private Long id;

    @NotBlank
    private String name;

    @Builder
    public CategoryRequestDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category toEntity() {
        return Category.builder().id(id).name(name).build();
    }

}

