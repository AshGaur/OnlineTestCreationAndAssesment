package com.testcreation.zuul.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class JdbcSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	MyUserDetailsService userDetailsService;
	
	
//	For customising authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.anyRequest()
		.permitAll();
//		//Admins
//		.antMatchers("/").permitAll()
//		.antMatchers("/student/**").permitAll()
//		.antMatchers("/admin/**").permitAll()
//		.antMatchers("/trainer/**").permitAll()
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
//		.and()
//		.formLogin();
	}
	
//	For customising authentication
	@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			
		auth.userDetailsService(userDetailsService);
		}
	
	
	@Bean
	PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
}
