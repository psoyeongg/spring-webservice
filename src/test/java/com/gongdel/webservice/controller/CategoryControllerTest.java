package com.gongdel.webservice.controller;

import com.gongdel.webservice.domain.category.Category;
import com.gongdel.webservice.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;
/*
    @Test
    public void categories() throws Exception {

        List<Category> categories = Arrays.asList(new Category(1L, "spring"),
                new Category(2L, "jpa"),
                new Category(3L, "java"),
                new Category(4L, "spring-boot"),
                new Category(5L, "javascript")
        );

        Page<Category> categoryPage = new PageImpl<>(categories);

        given(this.categoryService.findAll(anyObject())).willReturn(categoryPage);
        MvcResult mvcResult = this.mockMvc.perform(get("/categories"))
            .andExpect(status().isOk())
                .andReturn();



    }*/
}
