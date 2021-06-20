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
//import com.testcreation.zuul.security.service.StudentUserDetailsService;
//
//@Order(2)
//@Configuration
//@EnableWebSecurity
//public class StudentSecurityConfig extends WebSecurityConfigurerAdapter {
//	
//	@Autowired
//	StudentUserDetailsService userDetailsService;
//	
//	
////	For customising authorization
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//		.csrf().disable()
//		.authorizeRequests()
//		//Students
//		.antMatchers(HttpMethod.POST,"/student-port/students").permitAll()
//		.antMatchers("/student-port/students/all").hasRole("STUDENT")//.permitAll()
//		.antMatchers(HttpMethod.POST,"/student-port/students/add/**").permitAll()
//		.antMatchers(HttpMethod.PUT,"/student-port/students/update/**").hasAnyRole("ADMIN","STUDENT")
//		.antMatchers(HttpMethod.DELETE,"/student-port/students/delete/**").hasAnyRole("ADMIN","STUDENT")
//		//------- Results
//		.antMatchers(HttpMethod.POST,"/student-port/results").permitAll()
//		.antMatchers(HttpMethod.POST,"/student-port/results/add/**").hasAnyRole("ADMIN","STUDENT")
//		.antMatchers(HttpMethod.PUT,"/student-port/results/update/**").hasAnyRole("ADMIN","STUDENT")
//		.antMatchers(HttpMethod.DELETE,"/student-port/results/delete/**").hasAnyRole("ADMIN","STUDENT")
//		.antMatchers(HttpMethod.PUT,"/student-port/results/complete/**").hasAnyRole("ADMIN","STUDENT")
//		//------- Attempts
//		.antMatchers(HttpMethod.POST,"/student-port/attempts").hasAnyRole("ADMIN,STUDENT")
//		.antMatchers(HttpMethod.POST,"/student-port/attempts/add").hasAnyRole("ADMIN","STUDENT")
//		.anyRequest().authenticated()
//		.and()
//		.formLogin();
//	}
//	
////	For customising authentication
//	@Override
//		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//			auth.userDetailsService(userDetailsService);
//		}
//
//	
////	@Bean
////	PasswordEncoder getPasswordEncoder() {
////		return NoOpPasswordEncoder.getInstance();
////	}
//	
//}
