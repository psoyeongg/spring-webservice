package com.gongdel.webservice.service;

import com.gongdel.webservice.domain.category.Category;
import com.gongdel.webservice.domain.comment.CategoryRepository;
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
        Category category = new Category();
        category.setName("gongdel");

        // when
        Category category1 = categoryService.createCategory(category);

        // then
        assertThat(category.getName()).isEqualTo(category1.getName());
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
        Category category = new Category();
        category.setName("gongdel");
        long id = categoryService.createCategory(category).getId();

        // when
        Category entity = categoryService.findOne(id);

        // then
        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getName()).isEqualTo(category.getName());

    }


    @Test
    public void Dto데이터_변경() {
        // given
        Category category = new Category();
        category.setName("gongdel");
        long id = categoryService.createCategory(category).getId();
        Category category1 = new Category();
        category1.setName("gongdelUpdate");
        category1.setId(id);

        // when
        categoryService.updateCategory(category1);

        // then
        Category newCategory = categoryService.findOne(1L);
        assertThat(newCategory.getName()).isEqualTo(category1.getName());

    }
}

