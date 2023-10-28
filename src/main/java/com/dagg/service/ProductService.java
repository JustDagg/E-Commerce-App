package com.dagg.service;


import com.dagg.entity.Category;
import com.dagg.entity.Product;
import com.dagg.form.ProductFilterForm;
import com.dagg.form.ProductFormForCreating;
import com.dagg.form.ProductFormForUpdating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author Dagg
 */

public interface ProductService {

    List<Product> getAllListProducts();

    Page<Product> getAllProductsPaging(Pageable pageable, String search, ProductFilterForm productFilterForm);

    Optional<Product> getProductById(int id);

    List<Product> getAllProductsByCategoryId(int id);

    void createProduct(Product product);

    void updateProduct(int id, Product product);

    void deleteProductById(int id);

    void deleteMultipleProducts(List<Integer> ids);

}
