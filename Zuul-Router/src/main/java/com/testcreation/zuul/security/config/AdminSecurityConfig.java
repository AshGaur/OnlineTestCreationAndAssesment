package com.testcreation.zuul.security.config;



//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.testcreation.zuul.security.service.AdminUserDetailsService;
//
//@Order(1)
//@Configuration
//@EnableWebSecurity
//public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {
//	
//	@Autowired
//	AdminUserDetailsService userDetailsService;
//	
//	
////	For customising authorization
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//		.csrf().disable()
//		.authorizeRequests()
//		//Admins
//		.antMatchers("/").permitAll()
//		.antMatchers(HttpMethod.POST,"/admin-port/admins").permitAll()
//		.antMatchers("/admin-port/admins/all").hasRole("ADMIN")//.permitAll()
//		.antMatchers(HttpMethod.POST,"/admin-port/admins/add").permitAll()
//		.antMatchers(HttpMethod.PUT,"/admin-port/admins/update/**").hasRole("ADMIN")
//		.antMatchers(HttpMethod.DELETE,"/admin-port/admins/delete/**").hasRole("ADMIN")
//		//----- Categories
//		.antMatchers(HttpMethod.POST,"/admin-port/categories").permitAll()
//		.antMatchers("/admin-port/categories/all").permitAll()
//		.antMatchers(HttpMethod.POST,"/admin-port/categories/add").hasRole("ADMIN")
//		.antMatchers(HttpMethod.PUT,"/admin-port/categories/update/**").hasRole("ADMIN")
//		.antMatchers(HttpMethod.DELETE,"/admin-port/categories/delete/**").hasRole("ADMIN")
//		//------ Subscriptions
//		.antMatchers(HttpMethod.POST,"/admin-port/subscriptions").permitAll()
//		.antMatchers("/admin-port/subscriptions/all").permitAll()
//		.antMatchers(HttpMethod.POST,"/admin-port/subscriptions/add").hasRole("ADMIN")
//		.antMatchers(HttpMethod.PUT,"/admin-port/subscriptions/update/**").hasRole("ADMIN")
//		.antMatchers(HttpMethod.DELETE,"/admin-port/subscriptions/delete/**").hasRole("ADMIN")
//		.anyRequest().authenticated()
//		.and()
//		.formLogin();
//	}
//	
////	For customising authentication
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService);
//	}
//
//	
////	@Bean
////	PasswordEncoder getPasswordEncoder() {
////		return NoOpPasswordEncoder.getInstance();
////	}
//	
//}
