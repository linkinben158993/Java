package com.myclass.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan("com.myclass")
@Order(1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Cấu hình phân quyền
		http
		.csrf().disable()
		.antMatcher("/**") // Gặp mọi link đều kiểm tra quyền để tiện xài thymeleaf.
		.authorizeRequests()
		.antMatchers("/admin/login/**","/home/**") //Gặp admin/login thì cho vào 
		.permitAll()
		
		//Xem quyền và các người dùng thì cho Admin và Lecturer
		.antMatchers("/admin/users", "/admin/roles") // Gặp link này chỉ cho quyền ADMIN và LECTURER truy cập
		.hasAnyRole("ADMIN", "LECTURER","LEARNER")
		
		//Chỉnh sửa quyền chỉ có admin được
		.antMatchers("/admin/roles/**")
		.hasAnyRole("ADMIN")
		
		//Tự chỉnh sửa hồ sơ của bản thân
		
		
		.anyRequest() // Những link khác /admin/** cho truy cập thoải mái
		.permitAll();
		
		// Cấu hình đăng nhập
		http
		.formLogin()
		.loginProcessingUrl("/admin/login") // Link submit thông tin đăng nhập
		.loginPage("/admin/login")// Custom link show form đăng nhập (Mặc định là /login)
		.usernameParameter("email")
		.passwordParameter("password")
		.defaultSuccessUrl("/home")
		.failureUrl("/admin/login?error=true");
		
		// Cấu hình logout
		http.logout()
		.logoutUrl("/admin/logout")
		.logoutSuccessUrl("/admin/login")
		.invalidateHttpSession(true)
		.deleteCookies("JSESSIONID");
		
		// Cấu hình truy cập không đủ quyền
		http.exceptionHandling()
		.accessDeniedPage("/error/403");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/**");
	}
}
