package com.dagg.service.impl;

import com.dagg.entity.Category;
import com.dagg.exception.ResourceNotFoundException;
import com.dagg.repository.CategoryRepository;
import com.dagg.service.CategoryService;
import com.dagg.specification.CategorySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Dagg
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllListCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Page<Category> getAllCategoriesPaging(Pageable pageable, String search) {
        Specification<Category> where = CategorySpecification.buildWhere(search);
        return categoryRepository.findAll(where, pageable);
    }

    @Override
    public Optional<Category> getCategoryById(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void updateCategory(int id, Category category) {
        Category updateCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not exist with id: " + id));
        updateCategory.setName(category.getName());
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(int id) {
        categoryRepository.deleteById(id);
    }
}
