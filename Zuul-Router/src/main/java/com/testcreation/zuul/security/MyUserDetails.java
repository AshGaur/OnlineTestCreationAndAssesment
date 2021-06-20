package com.testcreation.zuul.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.testcreation.zuul.bean.User;

public class MyUserDetails implements UserDetails {
	
	String username;
	String password;
	Boolean active;
	
	List<SimpleGrantedAuthority> authorities;
	
	public MyUserDetails(User theUser) {
		System.out.println("MYUSERDETAILS :"+theUser);
		this.username = theUser.getPhone()==null?theUser.getEmail():theUser.getPhone();
		this.password = theUser.getPassword();
		this.active = theUser.isActive();
		this.authorities = Arrays.stream(theUser.getRoles().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return this.authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.active;
	}

}
