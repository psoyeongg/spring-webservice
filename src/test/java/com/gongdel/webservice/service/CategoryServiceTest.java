package com.gongdel.webservice.service;

import com.gongdel.webservice.domain.category.Category;
import com.gongdel.webservice.domain.category.CategoryRepository;
import com.gongdel.webservice.dto.category.CategoryRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @After
    public void cleanup() {
        categoryRepository.deleteAll();
    }

    @Test
    public void Dto데이터가_category테이블에_저장된다() {
        // given
        CategoryRequestDto categoryRequestDto = CategoryRequestDto.builder()
                .id(1L)
                .name("카테고리테스트")
                .build();


        // when
        Category category = categoryService.createCategory(categoryRequestDto);

        // then
        assertThat(category.getName()).isEqualTo(category.getName());
    }

    @Test
    public void Dto데이터가_조회된다() {
        // when
        List<Category> categories = categoryService.findAll();

        // then
        for (Category entity : categories) {
            System.out.println(entity.getId());
            System.out.println(entity.getName());
            System.out.println(entity.getCreatedDate());
        }
    }

    @Test
    public void Dto데이터_특정id조회() {
        // given
        CategoryRequestDto categoryRequestDto = CategoryRequestDto.builder()
                .id(1L)
                .name("카테고리테스트")
                .build();
        long id = categoryService.createCategory(categoryRequestDto).getId();

        // when
        Category entity = categoryService.findOne(id);

        // then
        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getName()).isEqualTo(categoryRequestDto.getName());

    }


    @Test
    public void Dto데이터_변경() {
        // given
        CategoryRequestDto categoryRequestDto = CategoryRequestDto.builder()
                .id(1L)
                .name("카테고리테스트")
                .build();
        long id = categoryService.createCategory(categoryRequestDto).getId();

        CategoryRequestDto categoryRequestDtoUpdate = CategoryRequestDto.builder()
                .id(id)
                .name("카테고리테스트update")
                .build();


        // when
        categoryService.updateCategory(categoryRequestDtoUpdate);

        // then
        Category newCategory = categoryService.findOne(id);
        assertThat(newCategory.getName()).isEqualTo(categoryRequestDtoUpdate.getName());

    }
}

