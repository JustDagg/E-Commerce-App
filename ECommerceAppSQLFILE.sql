
CREATE DATABASE e_commerce_app_docker;
USE e_commerce_app_docker;

-- create table: User
-- DROP TABLE IF EXISTS `User`;
CREATE TABLE `user` (
	id					INT AUTO_INCREMENT PRIMARY KEY,
	`name`				VARCHAR(30) NOT NULL,
    username			VARCHAR(30) NOT NULL,
    `password`			VARCHAR(800),
	email				VARCHAR(50) UNIQUE NOT NULL,	
    `status`			TINYINT DEFAULT 0
);

-- create table: Role
-- DROP TABLE IF EXISTS `Role`;
CREATE TABLE `role` (
	id					INT AUTO_INCREMENT PRIMARY KEY,
    `name`				VARCHAR(20)	NOT NULL
);

-- create table: UserRole
-- DROP TABLE IF EXISTS `User_Role`;
CREATE TABLE `user_role` (
	user_id				INT NOT NULL,
    role_id				INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES `user`(id),
    FOREIGN KEY (role_id) REFERENCES `role`(id),
    PRIMARY KEY (user_id, role_id)
);

/*============================== INSERT DATABASE =======================================*/
/*======================================================================================*/

-- password: 123456
-- Add data User
INSERT INTO `user` 		(`name`			,	username		, 	`password`														,	email						,	`status`)
VALUE					('Tuan Dang'	,	'tuandang'		, 	"$2a$10$oZ9ZXKVUQQSDKnsGm6P./O5IZIrpLDFEAlB2OctLPg.CkrMSP1NTm"	,	'tuandang2k2@gmail.com'		,	1		);
     
-- Add data Role
INSERT INTO `role`		(`name`					)
VALUE					('ROLE_USER'			),
						('ROLE_MANAGER'			),
						('ROLE_ADMIN'			),
						('ROLE_SUPER_ADMIN'		);
         
-- Add data User_Role
INSERT INTO `user_role`	(user_id	,	role_id	)
VALUE					(1			,	3		);