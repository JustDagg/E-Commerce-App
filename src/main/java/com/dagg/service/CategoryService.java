package com.dagg.service;

import com.dagg.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author Dagg
 */

public interface CategoryService {

    List<Category> getAllListCategories();

    Page<Category> getAllCategoriesPaging(Pageable pageable, String search);

    Optional<Category> getCategoryById(int id);

    void createCategory(Category category);

    void updateCategory(int id, Category category);

    void deleteCategoryById(int id);

}
