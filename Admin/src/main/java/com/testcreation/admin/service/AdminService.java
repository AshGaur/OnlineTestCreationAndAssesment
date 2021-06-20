package com.testcreation.admin.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.testcreation.admin.bean.Admin;
import com.testcreation.admin.dao.AdminRepository;

@Service
public class AdminService {
	@Autowired
	AdminRepository repo;
	
	public Iterable<Admin> getAllAdmins() {
		return repo.findAll();
	}
	
	public Optional<Admin> getAdminById(Integer id) {
		return repo.findById(id);
	}

	public void addAdmin(Admin theAdmin) {
		repo.save(theAdmin);
	}

	public void updateAdmin(Admin theAdmin) {
		repo.save(theAdmin);	
	}

	public void deleteAdmin(Integer id) {
		repo.deleteById(id);	
	}
	
	public Optional<Admin> getAdminByEmail(String email){
		return repo.findByEmail(email);
	}
	
}

