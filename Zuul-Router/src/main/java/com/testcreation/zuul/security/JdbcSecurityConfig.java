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
		//Admins
		.antMatchers("/").permitAll()
		.antMatchers(HttpMethod.POST,"/admin-port/admins").permitAll()
		.antMatchers("/admin-port/admins/all").hasRole("ADMIN")//.permitAll()
		.antMatchers(HttpMethod.POST,"/admin-port/admins/add").permitAll()
		.antMatchers(HttpMethod.PUT,"/admin-port/admins/update/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE,"/admin-port/admins/delete/**").hasRole("ADMIN")
		//----- Categories
		.antMatchers(HttpMethod.POST,"/admin-port/categories").permitAll()
		.antMatchers("/admin-port/categories/all").permitAll()
		.antMatchers(HttpMethod.POST,"/admin-port/categories/add").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT,"/admin-port/categories/update/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE,"/admin-port/categories/delete/**").hasRole("ADMIN")
		//------ Subscriptions
		.antMatchers(HttpMethod.POST,"/admin-port/subscriptions").permitAll()
		.antMatchers("/admin-port/subscriptions/all").permitAll()
		.antMatchers(HttpMethod.POST,"/admin-port/subscriptions/add").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT,"/admin-port/subscriptions/update/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE,"/admin-port/subscriptions/delete/**").hasRole("ADMIN")
		//Students
		.antMatchers("/student-port/students").permitAll()
		.antMatchers("/student-port/students/all").permitAll()
		.antMatchers("/student-port/students/add/**").permitAll()
		.antMatchers("/student-port/students/update/**").hasAnyRole("ADMIN","STUDENT")
		.antMatchers("/student-port/students/delete/**").hasAnyRole("ADMIN","STUDENT")
		//------- Results
		.antMatchers("/student-port/results").permitAll()
		.antMatchers("/student-port/results/add/**").hasAnyRole("ADMIN","STUDENT")
		.antMatchers("/student-port/results/update/**").hasAnyRole("ADMIN","STUDENT")
		.antMatchers("/student-port/results/delete/**").hasAnyRole("ADMIN","STUDENT")
		.antMatchers("/student-port/results/complete/**").hasAnyRole("ADMIN","STUDENT")
		//------- Attempts
		.antMatchers("/student-port/attempts").permitAll()
		.antMatchers("/student-port/attempts/add").permitAll()
		.antMatchers("/student-port/attempts/add").permitAll()
		//Trainers
		.antMatchers("/trainer-port/trainers").permitAll()
		.antMatchers("/trainer-port/trainers/add/**").permitAll()
		.antMatchers("/trainer-port/trainers/update/**").hasAnyRole("ADMIN","TRAINER")
		.antMatchers("/trainer-port/trainers/delete/**").hasAnyRole("ADMIN","TRAINER")
		//-------- Test
		.antMatchers("/trainer-port/tests").permitAll()
		.antMatchers("/trainer-port/tests/add/**").hasAnyRole("ADMIN","TRAINER")
		.antMatchers("/trainer-port/tests/update/**").hasAnyRole("ADMIN","TRAINER")
		.antMatchers("/trainer-port/tests/delete/**").hasAnyRole("ADMIN","TRAINER")
		//-------- Question
		.antMatchers("/trainer-port/questions").permitAll()
		.antMatchers("/trainer-port/questions/add/**").hasAnyRole("ADMIN","TRAINER")
		.antMatchers("/trainer-port/questions/update/**").hasAnyRole("ADMIN","TRAINER")
		.antMatchers("/trainer-port/questions/delete/**").hasAnyRole("ADMIN","TRAINER")
		.and()
		.formLogin();
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
