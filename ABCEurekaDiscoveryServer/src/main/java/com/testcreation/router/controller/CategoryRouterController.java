package com.testcreation.router.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testcreation.router.bean.Category;
import com.testcreation.router.service.CategoryRouterService;

@RestController
@RequestMapping("/category")
public class CategoryRouterController {
 
	@Autowired
	CategoryRouterService service;
	
	@GetMapping("/all")
	public List<Object> getAllCategory(){
		return service.getAllCategory();
	}
//	@GetMapping("/{id}")
//	public Object getCategoryById(@PathVariable Integer id) {
//		 return service.getCategoryById(id);
//	}
}