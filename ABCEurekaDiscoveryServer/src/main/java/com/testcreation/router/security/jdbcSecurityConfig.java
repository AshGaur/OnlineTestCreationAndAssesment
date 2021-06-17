package com.testcreation.router.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class jdbcSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
//	For customising authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/admin").hasRole("ADMIN")       //need to change url
		.antMatchers("/students").hasRole("STUDENT")  //need to change url
		.antMatchers("/trainers").hasRole("TRAINER")  //need to change url
		.antMatchers("/").permitAll()
		.and()
		.formLogin();
	}
	
//	For customising authentication
	@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			
		}
	
	
	@Bean
	PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	

}
