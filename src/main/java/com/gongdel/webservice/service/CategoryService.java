package com.gongdel.webservice.service;

import com.gongdel.webservice.domain.category.Category;
import com.gongdel.webservice.domain.category.CategoryRepository;
import com.gongdel.webservice.dto.category.CategoryRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category createCategory (CategoryRequestDto categoryRequestDto) {
        return categoryRepository.save(categoryRequestDto.toEntity());
    }

    @Transactional(readOnly = true)
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findOne(Long id) {
        return  categoryRepository.findOne(id);
    }

    public void delete(Long id) {
        categoryRepository.delete(id);
    }

    public void updateCategory(CategoryRequestDto categoryRequestDto) {
        Category oldCategory = categoryRepository.findOne(categoryRequestDto.getId());

        if (oldCategory != null) {
            oldCategory.setName(categoryRequestDto.getName());
        }
    }
}

