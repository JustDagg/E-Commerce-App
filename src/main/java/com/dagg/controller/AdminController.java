package com.dagg.controller;

import com.dagg.dto.*;
import com.dagg.dto.detail.DetailProductDTO;
import com.dagg.entity.Category;
import com.dagg.entity.Product;
import com.dagg.entity.Role;
import com.dagg.entity.User;
import com.dagg.form.*;
import com.dagg.service.CategoryService;
import com.dagg.service.ProductService;
import com.dagg.service.RoleService;
import com.dagg.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;

/**
 * @author Dagg
 */

@RestController
@RequestMapping("api")
public class AdminController {


    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/admin")
    public String adminHome() {
        return "adminHome";
    }

    @GetMapping("/admin/users")
    public List<UserDTO> getAllListUsers(){
        List<User> userList = userService.getAllListUsers();
        List<UserDTO> userDTOList = modelMapper.map(userList, new TypeToken<List<UserDTO>>() {}.getType());
        return userDTOList;
    }

    @GetMapping("/admin/users/paging")
    public Page<UserDTO> getAllUsersPaging(Pageable pageable, @RequestParam(value = "search", required = false) String search){
        Page<User> userPage = userService.getAllUsersPaging(pageable, search);
        List<UserDTO> dtos = modelMapper.map(userPage.getContent(), new TypeToken<List<UserDTO>>() {}.getType());
        Page<UserDTO> dtoPage = new PageImpl<>(dtos, pageable, userPage.getTotalElements());
        return dtoPage;
    }

//   ROLE
    @PostMapping("/admin/role/add-to-user")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm roleToUserForm) {
        userService.addRoleToUser(roleToUserForm.getUsername(), roleToUserForm.getRoleName());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/admin/role/create-role")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/admin/role/add-role").toUriString());
        return ResponseEntity.created(uri).body(userService.createRole(role));
    }

    @DeleteMapping("/admin/role/delete/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable(name = "id") int id){
        roleService.deleteRoleById(id);
        return new ResponseEntity<>("Role deleted! please redirect:/admin/role", HttpStatus.OK);
    }

//   USER
    @PostMapping("/admin/users")
    public ResponseEntity<?> addNewUser(@RequestBody UserFormForCreating userFormForCreating) {

//        User user = new User();
//        user.setId(userFormForCreating.getId());
//        user.setName(userFormForCreating.getName());
//        user.setUsername(userFormForCreating.getUsername());
//        user.setPassword(bCryptPasswordEncoder.encode(userFormForCreating.getPassword()));
//        user.setEmail(userFormForCreating.getEmail());
//        user.setStatus(User.UserStatus.valueOf(userFormForCreating.getStatus()));

        userService.addNewUser(userFormForCreating);
        return new ResponseEntity<>("Create user successfully! please redirect:/admin/users", HttpStatus.CREATED);
    }



    @PutMapping("/admin/users/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @ModelAttribute UserFormForUpdating userFormForUpdating){

        User user = new User();
        user.setId(userFormForUpdating.getId());
        user.setName(userFormForUpdating.getName());
        user.setUsername(userFormForUpdating.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userFormForUpdating.getPassword()));
        user.setEmail(userFormForUpdating.getEmail());
        user.setStatus(User.UserStatus.valueOf(userFormForUpdating.getStatus()));

        userService.updateUser(id, user);

        return new ResponseEntity<>("Update user successfully! please redirect:/admin/users", HttpStatus.OK);
    }

    @DeleteMapping("/admin/users/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") int id){
        userService.deleteUserById(id);
        return new ResponseEntity<>("User deleted! please redirect:/admin/users", HttpStatus.OK);
    }

    @DeleteMapping("/admin/users/delete-multiple")
    public ResponseEntity<?> deleteMultipleUsers(@RequestParam(value = "ids") List<Integer> ids){
        userService.deleteMultipleUsers(ids);
        return new ResponseEntity<>("Multiple users deleted! please redirect:/admin/users", HttpStatus.OK);
    }

//   CATEGORIES
    @GetMapping("/admin/categories")
    public List<CategoryDTO> getAllListCategories() {
        List<Category> categoryList = categoryService.getAllListCategories();
        List<CategoryDTO> categoryDTOList = modelMapper.map(categoryList, new TypeToken<List<CategoryDTO>>() {}.getType());
        return categoryDTOList;
    }

    @GetMapping("/admin/categories/paging")
    public Page<CategoryDTO> getAllCategoriesPaging(Pageable pageable, @RequestParam(value = "search", required = false) String search) {
        Page<Category> categoryPage = categoryService.getAllCategoriesPaging(pageable, search);
        List<CategoryDTO> dtos = modelMapper.map(categoryPage.getContent(), new TypeToken<List<CategoryDTO>>() {}.getType());
        Page<CategoryDTO> categoryDTOPage = new PageImpl<>(dtos, pageable, categoryPage.getTotalElements());
        return categoryDTOPage;
    }

    @PostMapping("/admin/categories")
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        categoryService.createCategory(category);
        return new ResponseEntity<>("Create category successfully! please redirect:/admin/categories", HttpStatus.CREATED);
    }

    @PutMapping("admin/categories/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable(name = "id") int id, @RequestBody Category category) {
        categoryService.updateCategory(id, category);
        return new ResponseEntity<>("Update category successfully! please redirect:/admin/categories", HttpStatus.OK);
    }

    @DeleteMapping("admin/categories/delete/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable(name = "id") int id) {
        categoryService.deleteCategoryById(id);
        return new ResponseEntity<>("Category deleted! please redirect:/admin/categories", HttpStatus.OK);
    }

//   PRODUCT
    @GetMapping("/admin/products")
    public List<ProductDTO> getAllListProducts() {
        List<Product> productList = productService.getAllListProducts();
        List<ProductDTO> productDTOList = modelMapper.map(productList, new TypeToken<List<ProductDTO>>() {}.getType());
        return productDTOList;
}

    @GetMapping("/admin/products/detail")
    public List<DetailProductDTO> getAllDetailListProducts() {
        List<Product> productList = productService.getAllListProducts();
        List<DetailProductDTO> productDTOList = modelMapper.map(productList, new TypeToken<List<DetailProductDTO>>() {}.getType());
        return productDTOList;
    }

    @GetMapping("/admin/products/paging")
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
    }

    @GetMapping("/admin/products/detail/paging")
    public ResponseEntity<?> getAllDetailProductsPaging(Pageable pageable,
                                                        @RequestParam(value = "search", required = false) String search,
                                                        ProductFilterForm productFilterForm) {
        Page<Product> productPage = productService.getAllProductsPaging(pageable, search, productFilterForm);
        Page<DetailProductDTO> dtoPage = productPage.map(new Function<Product, DetailProductDTO>() {
            @Override
            public DetailProductDTO apply(Product entity) {

                DetailProductDTO dto = new DetailProductDTO(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getWeight(),
                entity.getDescription(),
                entity.getImageName(),
                    new CategoryDTO(
                            entity.getCategory().getId(),
                            entity.getCategory().getName()));
                return dto;
            }
        });
        return new ResponseEntity<>(dtoPage, HttpStatus.OK);
    }

    @PostMapping("/admin/products")
    public ResponseEntity<?> createProduct( @ModelAttribute ProductFormForCreating productFormForCreating,
                                            @RequestParam("productImage") MultipartFile file,
                                            @RequestParam("imageName") String imageName) throws IOException {
        Product product = new Product();
        product.setId(productFormForCreating.getId());
        product.setName(productFormForCreating.getName());
        product.setAvailableQuantity(productFormForCreating.getAvailableQuantity());
        product.setPrice(productFormForCreating.getPrice());
        product.setWeight(productFormForCreating.getWeight());
        product.setDescription(productFormForCreating.getDescription());
        String imageUUID;
        if (!file.isEmpty()) {
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        } else {
            imageUUID = imageName;
        }
        product.setImageName(imageUUID);
        product.setCategory(categoryService.getCategoryById(productFormForCreating.getCategoryId()).get());

        productService.createProduct(product);
        return new ResponseEntity<>("Create product successfully! please redirect:/admin/products", HttpStatus.CREATED);
    }

    @PutMapping("admin/products/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable(name = "id") int id, @ModelAttribute ProductFormForUpdating productFormForUpdating) {

        Product product = new Product();
        product.setId(productFormForUpdating.getId());
        product.setName(productFormForUpdating.getName());
        product.setAvailableQuantity(productFormForUpdating.getAvailableQuantity());
        product.setPrice(productFormForUpdating.getPrice());
        product.setWeight((productFormForUpdating.getWeight()));
        product.setDescription(productFormForUpdating.getDescription());
        product.setImageName(productFormForUpdating.getImageName());
        product.setCategory(categoryService.getCategoryById(productFormForUpdating.getCategoryId()).get());

        productService.updateProduct(id, product);
        return new ResponseEntity<>("Update product successfully! please redirect:/admin/products", HttpStatus.OK);
}

    @DeleteMapping("admin/products/delete/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable(name = "id") int id) {
        productService.deleteProductById(id);
        return new ResponseEntity<>("Order deleted! please redirect:/admin/products", HttpStatus.OK);
    }

    @DeleteMapping("admin/products/delete-multiple")
    public ResponseEntity<?> deleteMultipleProducts(@RequestParam(value = "ids") List<Integer> ids) {
        productService.deleteMultipleProducts(ids);
        return new ResponseEntity<>("Multiple products deleted! please redirect:/admin/products", HttpStatus.OK);
    }


}
