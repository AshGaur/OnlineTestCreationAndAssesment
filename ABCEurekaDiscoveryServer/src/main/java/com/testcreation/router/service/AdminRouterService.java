package com.testcreation.router.service;

import java.util.Arrays;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.testcreation.router.bean.Admin;
import com.testcreation.router.exception.RestTemplateResponseErrorHandler;

@Service
public class AdminRouterService {
	
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
		String url = "http://localhost:8080/admins/all";
		return Arrays.asList(restTemplate.getForObject(url, Admin[].class));
	}
	
	public List<Admin> saveAdmins(){
		return Arrays.asList(new Admin(-1,"Admin Service will return in sometime","Service Unavailable"));
	}
	
	public Admin getAdminById(Integer id) {
		String url = "http://localhost:8080/admins/"+id.toString();
		return restTemplate.getForObject(url, Admin.class);
	}
	
}
