package com.testcreation.router.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.testcreation.router.bean.Admin;

@Service
public class AdminRouterService {
	
	@Autowired
	RestTemplate restTemplate;
	
	
//	public void addAdmin(Admin admin) {
//		String url = "http://localhost:8080/admins/add";
//		restTemplate.postForObject(url, admin, Void.class);
//	}
	
	public List<Object> getAllAdmins() {
		String url = "http://localhost:8080/admins/all";
		return Arrays.asList(restTemplate.getForObject(url, Object[].class));
	}


	public Object getAdminById(Integer id) {
		String url = "http://localhost:8080/admins/"+id.toString();
		return restTemplate.getForObject(url, Object.class);
	}
	
}
