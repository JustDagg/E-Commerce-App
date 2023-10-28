package com.dagg.security;


import com.dagg.filter.CustomAuthenticationFilter;
import com.dagg.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Dagg
 */

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

//    @Autowired
//    GoogleOAuth2SuccessHandler googleOAuth2SuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/",
                                        "/api/home/**",
                                        "/api/login/**",
                                        "/api/register/**",
                                        "/api/active_account",
                                        "/api/token/refresh/**",
                                        "/api/shop/**",
                                            "/api/shop/category/**",
                                            "/api/shop/products/**"
                            ).permitAll();

        http
                .authorizeRequests()
                        .antMatchers("/api/username/{username}",
                                                "/api/email/{email}",
                                                "/api/password/password-changing",
                                                "/api/carts/**",
                                                    "/api/carts/products/**",
                                                    "/api/carts/get-order/**",
                                                    "/api/carts/place-order/**"
                                                                        ).hasAnyAuthority("ROLE_USER",
                                                                                                    "ROLE_ADMIN",
                                                                                                    "ROLE_MANAGER",
                                                                                                    "ROLE_SUPER_ADMIN");

        http
                .authorizeRequests()
                .antMatchers("/api/admin/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_SUPER_ADMIN");

//        http
//                .cors()
//                .and()
//                .oauth2Login()
//                .loginPage("/login")
//                .successHandler(googleOAuth2SuccessHandler);

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**",
                "/static/**",
                "/productImages/**",
                "/css/**",
                "/js/**");
    }//bỏ qua authentication cho các package này

}
