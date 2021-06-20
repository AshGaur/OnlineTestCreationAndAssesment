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
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.testcreation.zuul.security.service.TrainerUserDetailsService;
//
//@Order(3)
//@Configuration
//@EnableWebSecurity
//public class TrainerSecurityConfig extends WebSecurityConfigurerAdapter {
//	
//	@Autowired
//	TrainerUserDetailsService userDetailsService;
//	
//	
////	For customising authorization
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//		.csrf().disable()
//		.authorizeRequests()
//		//Trainers
//		.antMatchers(HttpMethod.POST,"/trainer-port/trainers").permitAll()
//		.antMatchers("/trainer-port/trainers/all").hasRole("TRAINER")
//		.antMatchers(HttpMethod.POST,"/trainer-port/trainers/add/**").permitAll()
//		.antMatchers(HttpMethod.PUT,"/trainer-port/trainers/update/**").hasAnyRole("ADMIN","TRAINER")
//		.antMatchers(HttpMethod.DELETE,"/trainer-port/trainers/delete/**").hasAnyRole("ADMIN","TRAINER")
//		//-------- Test
//		.antMatchers(HttpMethod.POST,"/trainer-port/tests").permitAll()
//		.antMatchers(HttpMethod.POST,"/trainer-port/tests/add/**").hasAnyRole("ADMIN","TRAINER")
//		.antMatchers(HttpMethod.PUT,"/trainer-port/tests/update/**").hasAnyRole("ADMIN","TRAINER")
//		.antMatchers(HttpMethod.DELETE,"/trainer-port/tests/delete/**").hasAnyRole("ADMIN","TRAINER")
//		//-------- Question
//		.antMatchers(HttpMethod.POST,"/trainer-port/questions").permitAll()
//		.antMatchers(HttpMethod.POST,"/trainer-port/questions/add/**").hasAnyRole("ADMIN","TRAINER")
//		.antMatchers(HttpMethod.PUT,"/trainer-port/questions/update/**").hasAnyRole("ADMIN","TRAINER")
//		.antMatchers(HttpMethod.DELETE,"/trainer-port/questions/delete/**").hasAnyRole("ADMIN","TRAINER")
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
//	@Bean
//	PasswordEncoder getPasswordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
//	
//}
