package com.testcreation.admin.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.testcreation.admin.bean.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

}
