package com.example.springrest.web.controller;

import com.example.springrest.entity.Category;
import com.example.springrest.mapper.CategoryMapper;
import com.example.springrest.service.impl.CategoryService;
import com.example.springrest.web.filter.CategoryFilter;
import com.example.springrest.web.model.CategoryListResponse;
import com.example.springrest.web.model.CategoryResponse;
import com.example.springrest.web.model.UpsertCategoryRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<CategoryListResponse> findAll(
            @Valid CategoryFilter categoryFilter) {

        return ResponseEntity.ok(
                categoryMapper.categoryListToCategoryListResponse(
                categoryService.findAllFiltered(categoryFilter)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                categoryMapper.categoryToResponse(categoryService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> insert(
            @RequestBody @Valid UpsertCategoryRequest request) {
        Category category = categoryService.insert(
                categoryMapper.requestToCategory(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryMapper.categoryToResponse(category));

    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(
            @PathVariable Long id, @RequestBody UpsertCategoryRequest request){
        Category category = categoryService.update(
                categoryMapper.requestToCategory(id, request));

        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryMapper.categoryToResponse(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
