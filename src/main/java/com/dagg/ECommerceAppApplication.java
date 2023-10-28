package com.dagg;

import com.dagg.entity.Role;
import com.dagg.entity.User;
import com.dagg.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;

@SpringBootApplication
public class ECommerceAppApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceAppApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	CommandLineRunner run(UserService userService) {
//		return args -> {
//			userService.saveRole(new Role(null, "ROLE_USER"));
//			userService.saveRole(new Role(null, "ROLE_MANAGER"));
//			userService.saveRole(new Role(null, "ROLE_ADMIN"));
//			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
//
//			userService.saveUser(new User(null, "Tuan Dang", "tuandang", "123456", new ArrayList<>()));
//			userService.saveUser(new User(null, "Dagg", "dagg", "123456", new ArrayList<>()));
//			userService.saveUser(new User(null, "Minh Tuan", "tuan", "123456", new ArrayList<>()));
//			userService.saveUser(new User(null, "Hoang Tuan", "hoangtuns", "123456", new ArrayList<>()));
//
//			userService.addRoleToUser("dagg", "ROLE_MANAGER");
//			userService.addRoleToUser("tuan", "ROLE_USER");
//			userService.addRoleToUser("tuandang", "ROLE_ADMIN");
//			userService.addRoleToUser("hoangtuns", "ROLE_SUPER_ADMIN");
//		};
//	}

	@SuppressWarnings("deprecation")
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry
						.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods("GET", "POST", "PUT", "DELETE");
			}
		};
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ECommerceAppApplication.class);
	}

}
