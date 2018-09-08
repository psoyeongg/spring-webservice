package com.gongdel.webservice.web;

import com.gongdel.webservice.dto.category.CategoryRequestDto;
import com.gongdel.webservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    @GetMapping
    public String categories(Pageable pageable, Model model) {
        model.addAttribute("categories", categoryService.findAll(pageable));
        return "category/list";
    }

    // 등록
    @GetMapping("/new")
    public String newCategory(@ModelAttribute CategoryRequestDto dto) {
        return "category/new";
    }
    @PostMapping("/new")
    public String createCategory(@ModelAttribute @Valid CategoryRequestDto categoryRequestDto, BindingResult result) {
        if (result.hasErrors()) {
            return "category/new";
        }
        categoryService.createCategory(categoryRequestDto);

        return "redirect:/categories";
    }

    // 수정
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        return "category/edit";
    }
    @PostMapping("/{id}/edit")
    public String modifyCategory(@PathVariable Long id, @ModelAttribute CategoryRequestDto categoryRequestDto,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return "category/edit";
        }
        categoryService.updateCategory(categoryRequestDto);

        return "redirect:/categories";
    }

    // 삭제
    @GetMapping("/{id}/delete")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return "redirect:/categories";
    }
}
