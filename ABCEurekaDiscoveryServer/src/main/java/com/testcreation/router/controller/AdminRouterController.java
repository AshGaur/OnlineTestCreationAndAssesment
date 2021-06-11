package com.testcreation.router.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testcreation.router.bean.Admin;
import com.testcreation.router.service.AdminRouterService;

@RestController
@RequestMapping("/admins")
public class AdminRouterController {
	@Autowired
	AdminRouterService service;
	
	@GetMapping("/all")
	List<Object> getAllAdmins() {
		return service.getAllAdmins();
	}
//	
	@GetMapping("/{id}")
	Object getAdminById(@PathVariable Integer id){
		return service.getAdminById(id);
	}
	
//	@PostMapping("/add")
//	public String addAdmin(@RequestBody Admin theAdmin) {
//		service.addAdmin(theAdmin);
//		return "Admin added successfully !";
//	}
	
//	@PutMapping("/update/{id}")
//	void updateAdmin(@RequestBody Admin theAdmin,@PathVariable Integer id) {
//		if(service.getAdminById(id).isEmpty())
//			throw new AdminException("Admin doesn't exist with ID provied !");
//		service.updateAdmin(theAdmin);
//	}
//	
//	@DeleteMapping("/delete/{id}")
//	void deleteAdmin(@PathVariable Integer id) {
//		if(service.getAdminById(id).isEmpty())
//			throw new AdminException("Admin doesn't exist with ID provied !");
//		service.deleteAdmin(id);
//	}	
}



