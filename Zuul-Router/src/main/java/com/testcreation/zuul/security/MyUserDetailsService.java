package com.testcreation.zuul.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.testcreation.zuul.bean.User;
import com.testcreation.zuul.service.UserService;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserService service;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = service.getAdminByEmail(username);
		
		if(user.isEmpty()) {
			user = service.getStudentByEmailOrPhone(username);
			user = user.get().getId()==null?Optional.empty():user;
		}
		
		if(user.isEmpty()) {
			user = service.getTrainerByEmailOrPhone(username);
			user = user.get().getId()==null?Optional.empty():user;
		}
		
		user.orElseThrow(() -> new UsernameNotFoundException("User not found : " + username));
		
		return user.map(MyUserDetails::new).get();
	}
	
}
