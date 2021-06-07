package com.testcreation.admin.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testcreation.admin.bean.Category;
import com.testcreation.admin.exception.CategoryException;
import com.testcreation.admin.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	@Autowired
	CategoryService service;
	
	@GetMapping("/all")
	Iterable<Category> getAllCategories() {
		return service.getAllCategories();
	}
	
	@RequestMapping("/{id}")
	Optional<Category> getCategoryById(@PathVariable int id){
		if(service.getCategoryById(id).isEmpty())
			throw new CategoryException("Category ID not found !");
		return service.getCategoryById(id);
	}
	
	@PostMapping("/add")
	void addCategory(@RequestBody Category theCategory) {
		service.addCategory(theCategory);
	}
	
	@PutMapping("/update/{id}")
	void updateCategory(@RequestBody Category theCategory,@PathVariable Integer id) {
		if(service.getCategoryById(id).isEmpty())
			throw new CategoryException("Category ID not found !");
		service.updateCategory(theCategory);
	}
	
	@DeleteMapping("/delete/{id}")
	void deleteCategory(@PathVariable int id) {
		if(service.getCategoryById(id).isEmpty())
			throw new CategoryException("Category ID not found !");
		service.deleteCategory(id);
	}
}
