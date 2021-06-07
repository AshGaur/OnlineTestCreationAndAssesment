package com.testcreation.trainer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.testcreation.trainer.bean.Question;

@Transactional
@Repository
public interface QuestionRepository extends CrudRepository<Question, Integer>{
	List<Question> findByTestId(Integer testId);
	
	@Modifying
	@Query("delete from questions q where q.test.id = :testId")
	void deleteByTestId(@Param(value = "testId") Integer testId);
}
