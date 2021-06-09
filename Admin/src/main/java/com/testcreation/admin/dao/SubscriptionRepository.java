package com.testcreation.admin.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.testcreation.admin.bean.Subscription;


@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {

}

