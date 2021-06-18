package com.testcreation.router.service;

import java.util.Arrays;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.testcreation.router.bean.Admin;

@Service
public class AdminRouterService {
	
	private static final String admin = null;

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	HttpHeaders headers;
	
	public ResponseEntity<String> addAdmin(String admin) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(admin, headers);
		String url = "http://localhost:8080/admins/add";
		return restTemplate.exchange(url,HttpMethod.POST ,request, String.class);
	}
	
	@HystrixCommand(fallbackMethod="saveAdmins")
	public List<Admin> getAllAdmins() {
		String url = "http://admin-microservice/admin-port/admins/all";
		return Arrays.asList(restTemplate.getForObject(url, Admin[].class));
	}
	
	public List<Admin> saveAdmins(){
		return Arrays.asList(new Admin(-1,"Admin Service will return in sometime","Service Unavailable"));
	}
	
	public Admin getAdminById(Integer id) {
		String url = "http://localhost:8080/admins/"+id.toString();
		return restTemplate.getForObject(url, Admin.class);
	}
	public ResponseEntity<String> updateAdmin(Integer id) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(admin, headers);
		String url = "http://localhost:8080/admins/add";
		return restTemplate.exchange(url,HttpMethod.PUT ,request, String.class);
	}
	public ResponseEntity<String> deleteAdmin(Integer id) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>("", headers);
		String url = "http://localhost:8080/admins/delete/"+id.toString();
		return restTemplate.exchange(url,HttpMethod.DELETE ,request, String.class);
	}
}
