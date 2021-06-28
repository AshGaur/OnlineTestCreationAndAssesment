package com.testcreation.admin.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.testcreation.admin.bean.Subscription;


@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {

	@Query("select i from subscriptions i where i.role=:role or i.role='All'")
	List<Subscription> findByRole(@Param(value="role") String role);

}

