# E-Commerce-App
Springboot, MySQL, Docker 


E-COMMERCE-APP BACKEND(SPRINGBOOT) AND DATABASE(MYSQL) (author: Dang Minh Tuan - Dagg)

-- VERSION
- IntelliJ IDEA Community Edition 2022.1.3
- JDK: 11.0.11
- SPRING: 2.5.2
- MYSQL DATABASE 8.0.29 
- DOCKER DESKTOP  24.0.6, build ed223bc

-- TECHNOLOGIES USED
- Database: MySQL

- Backend (Springboot Framework)    - Java
                                    - Spring Security (Authentication, Authorization, JWT(3.18.1), Google Oauth2)
                                    - Spring Data JPA
                                    - Spring Mail
                                    - Spring Specification

- Deploy: Docker (Backend)



-- DESCRIPTION
- WEB bán hàng với các đối tượng sử dụng: người dùng(ROLE_USER), quản lý(ROLE_MANAGER), admin(ROLE_ADMIN), siêu admin(ROLE_SUPER_ADMIN)  

- Lập trình BackEnd với các chức năng:  Đăng nhập(JWT có token và refresh-token và đăng nhập Google Oauth2), đăng kí có gửi mail về để xác nhận kích hoạt (active) tài khoản, phân quyền, 
                                            thay đổi mật khẩu, xem được (tài khoản, email).
                                        Quản lý Admin: CRUD người dùng users(trong đó có xóa nhiều users), vai trò roles(thêm và xóa), thêm vai trò cho các users.
                                        Quản lý sản phẩm(Products): upload ảnh sản phẩm (products), CRUD (sản phẩm(products), phân loại(categories)), xóa nhiều sản phẩm.
                                        Quản lý giỏ hàng(ShoppingCart): thêm, xóa giỏ hàng.
                                        Phân trang, tìm kiếm, bộ lọc, validate, exception handling, trang quản lý Admin, , trang chủ.
                                           

    + Với các chức năng: thiết kế API 
    
            #Về khách lạ:
                có thể vào trang xem trang chủ (home), các sản phẩm được trưng bày (shop), 
                xem các loại (categories), xem các loại products
                phân trang (paging),
                sắp xếp (sorting)
                tìm kiếm sản phẩm (tên product, tên phân loại(categories)), 
                đăng nhập(JWT có token và refresh-token và đăng nhập Google Oauth2),
                sử dụng bộ lọc (filter)

            #Về người dùng (ROLE_USER): 
                có thể vào trang xem trang chủ (home), các sản phẩm được trưng bày (shop), 
                xem các loại (categories), xem các loại products
                phân trang (paging),
                sắp xếp (sorting)
                tìm kiếm sản phẩm (tên product, tên phân loại(categories)), 
                sử dụng bộ lọc (filter), 
                đăng nhập(JWT có token và refresh-token và đăng nhập Google Oauth2)
                đăng ký có gửi mail về để xác nhận active tài khoản, 
                xem được tên username và email của mình khi đăng nhập,
                đổi mật khẩu,
                quản lý giỏ hàng (xem, thêm, xóa giỏ hàng)

            #Về các quyền (ROLE_MANAGER, ROLE_ADMIN, ROLE_SUPER_ADMIN):
                có thể vào trang xem trang chủ (home), các sản phẩm được trưng bày (shop), 
                xem các loại (categories), xem các loại products
                xem danh sách chi tiết về products
                phân trang (paging),
                sắp xếp (sorting)
                tìm kiếm sản phẩm (tên product, tên phân loại(categories)), 
                sử dụng bộ lọc (filter), 
                đăng nhập(JWT có token và refresh-token và đăng nhập Google Oauth2) 
                đăng ký có gửi mail về để xác nhận active tài khoản, 
                xem được tên username và email của mình khi đăng nhập,
                đổi mật khẩu,
                CRUD products(trong đó có xoá nhiều sản phẩm), categories, users(trong đó có xóa nhiều người dùng)
                thêm, xóa vai trò (role) cho user,
                thêm mới vai trò (role),
                quản lý giỏ hàng (xem, thêm, xóa giỏ hàng)

