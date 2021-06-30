package com.testcreation.students.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import com.testcreation.students.bean.Attempt;

@Transactional
public interface AttemptRepository extends CrudRepository<Attempt, Integer> {

	List<Attempt> findByResultListId(Integer resultId);

	List<Attempt> findByResultListIdAndQuestionListId(Integer resultId, Integer questionId);

	@Modifying
	void deleteByResultListId(Integer resultId);

}
