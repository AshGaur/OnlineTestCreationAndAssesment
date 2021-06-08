package com.testcreation.admin.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testcreation.admin.bean.Category;
import com.testcreation.admin.dao.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository repo;
	
	public Iterable<Category> getAllCategories() {
		return repo.findAll();
	}
	
	public Optional<Category> getCategoryById(int id) {
		return repo.findById(id);
	}
	
	public Optional<Category> getCategoryByName(String categoryName){
		return repo.findByCategoryName(categoryName);
	}
	
	public void addCategory(Category thecategory) {
		repo.save(thecategory);
	}

	public void updateCategory(Category thecategory) {
		repo.save(thecategory);	
	}

	public void deleteCategory(int id) {
		repo.deleteById(id);	
	}
	
	public void deleteCategoryByName(String categoryName) {
		repo.deleteByCategoryName(categoryName);
	}
}
