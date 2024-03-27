package com.example.springrest.service.impl;

import com.example.springrest.entity.Category;
import com.example.springrest.exception.EntityNotFoundException;
import com.example.springrest.repository.CategoryRepository;
import com.example.springrest.service.CRUDService;
import com.example.springrest.utils.BeanUtils;
import com.example.springrest.web.filter.CategoryFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements CRUDService<Category> {

    private final CategoryRepository categoryRepository;

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException(
                        MessageFormat.format("Категория с id {0} не найдена", id)));
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category insert(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        Category existedCategory = findById(category.getId());

        BeanUtils.copyNonNullProperties(category, existedCategory);

        return categoryRepository.save(existedCategory);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    public List<Category> findAllFiltered(CategoryFilter categoryFilter) {
        return categoryRepository.findAll(PageRequest.of(
                categoryFilter.getPageNumber(), categoryFilter.getPageSize()))
                .getContent();
    }
}
