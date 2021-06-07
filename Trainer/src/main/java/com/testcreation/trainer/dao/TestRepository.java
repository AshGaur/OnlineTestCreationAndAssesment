package com.testcreation.trainer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.testcreation.trainer.bean.Test;

@Transactional
@Repository
public interface TestRepository extends CrudRepository<Test, Integer> {
	List<Test> findByTrainerId(Integer trainerId);

	@Modifying
	@Query("delete from tests t where t.trainer.id = :trainerId")
	void deleteByTrainerId(@Param(value = "trainerId") Integer trainerId);
}