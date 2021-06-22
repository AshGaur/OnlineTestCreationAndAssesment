package com.testcreation.zuul.dao;



import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.testcreation.zuul.bean.User;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, String>{

	@Modifying
	@Query("delete from users i where i.email=:email")
	void deleteByEmail(@Param(value="email") String email);

	Optional<User> findByEmail(String email);
	
	
}
