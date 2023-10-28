package com.dagg.service.impl;

import com.dagg.entity.Category;
import com.dagg.entity.Product;
import com.dagg.form.ProductFilterForm;
import com.dagg.form.ProductFormForUpdating;
import com.dagg.repository.CategoryRepository;
import com.dagg.repository.ProductRepository;
import com.dagg.service.ProductService;
import com.dagg.specification.ProductSpecification;
import org.modelmapper.ModelMapper;
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
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Product> getAllListProducts() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> getAllProductsPaging(Pageable pageable, String search, ProductFilterForm productFilterForm) {
        Specification<Product> where = ProductSpecification.buildWhere(search, productFilterForm);
        return productRepository.findAll(where, pageable);
    }

    @Override
    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAllProductsByCategoryId(int id) {
        return productRepository.findAllByCategoryId(id);
    }

    @Override
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void updateProduct(int id, Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProductById(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public void deleteMultipleProducts(List<Integer> ids) {
        productRepository.deleteMultipleProducts(ids);
    }
}
