package com.testcreation.admin.dao;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.testcreation.admin.bean.Admin;

@Repository
public interface AdminRepository extends CrudRepository<Admin,Integer> {
	Optional<Admin> findByEmail(String email);
}
