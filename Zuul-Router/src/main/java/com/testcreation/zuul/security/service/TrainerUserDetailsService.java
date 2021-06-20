package com.testcreation.zuul.security.service;

//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.testcreation.zuul.bean.User;
//import com.testcreation.zuul.security.MyUserDetails;
//import com.testcreation.zuul.service.UserService;
//
//@Service
//public class TrainerUserDetailsService implements UserDetailsService{
//
//	@Autowired
//	UserService service;
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Optional<User> user = service.getTrainerByEmailOrPhone(username); 
//		
//		System.out.println("TRAINERSERVICE:"+user);
//		
//		user = user.isPresent() && user.get().getId()==null?Optional.empty():user;
//		
//		user.orElseThrow(() -> new UsernameNotFoundException("Trainer not found : " + username));
//		
//		return user.map(MyUserDetails::new).get();
//	}
//
//	
//}
