package com.testcreation.admin.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.testcreation.admin.bean.Category;

@Transactional
@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
	Optional<Category> findByCategoryName(String categoryName);
	
	void deleteByCategoryName(String categoryName);
}
