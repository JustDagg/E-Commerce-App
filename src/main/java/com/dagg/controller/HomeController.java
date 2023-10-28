package com.dagg.controller;

import com.dagg.dto.CategoryDTO;
import com.dagg.dto.ProductDTO;
import com.dagg.dto.detail.DetailProductDTO;
import com.dagg.entity.Category;
import com.dagg.entity.Product;
import com.dagg.form.ProductFilterForm;
import com.dagg.service.CategoryService;
import com.dagg.service.ProductService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;

/**
 * @author Dagg
 */

@RestController
@RequestMapping("api")
public class HomeController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/shop/categories")
    public Page<CategoryDTO> getAllCategoriesPaging(Pageable pageable, String search) {
        Page<Category> categoryPage = categoryService.getAllCategoriesPaging(pageable, search);
        List<CategoryDTO> dtos = modelMapper.map(categoryPage.getContent(), new TypeToken<List<CategoryDTO>>() {}.getType());
        Page<CategoryDTO> categoryDTOPage = new PageImpl<>(dtos, pageable, categoryPage.getTotalElements());
        return categoryDTOPage;
    } //view All Categories

    @GetMapping("/shop/categories/{id}")
    public List<ProductDTO> getAllProductsByCategoryId(@PathVariable int id){
        List<Product> productList = productService.getAllProductsByCategoryId(id);
        List<ProductDTO> productDTOList = modelMapper.map(productList, new TypeToken<List<ProductDTO>>() {}.getType());
        return productDTOList;
    } //view Products By CategoryId

    @GetMapping("/shop/products")
    public ResponseEntity<?> getAllProductsPaging(Pageable pageable,
                                                  @RequestParam(value = "search", required = false) String search,
                                                  ProductFilterForm productFilterForm
    ) {
        System.out.println("productFilterForm: " + productFilterForm.toString());
        Page<Product> productPage = productService.getAllProductsPaging(pageable, search, productFilterForm);
        Page<ProductDTO> dtoPage = productPage.map(new Function<Product, ProductDTO>() {
            @Override
            public ProductDTO apply(Product entity) {

                ProductDTO dto = new ProductDTO(
                        entity.getId(),
                        entity.getName(),
                        entity.getImageName());
                return dto;
            }
        });
        return new ResponseEntity<>(dtoPage, HttpStatus.OK);
    } //view All Products

    @GetMapping("/shop/products/{id}")
    public ProductDTO getProductById(@PathVariable int id){
        Product product =  productService.getProductById(id).get();
        System.out.println(product);
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        return productDTO;
    } //view product by id

    @GetMapping("/shop/products/detail/{id}")
    public DetailProductDTO getProductDetailById(@PathVariable int id){
        Product product =  productService.getProductById(id).get();
        System.out.println(product);
        DetailProductDTO detailProductDTO = modelMapper.map(product, DetailProductDTO.class);
        return detailProductDTO;
    } //view product Details
}
