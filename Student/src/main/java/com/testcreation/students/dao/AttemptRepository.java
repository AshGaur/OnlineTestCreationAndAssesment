package com.testcreation.students.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.testcreation.students.bean.Attempt;

public interface AttemptRepository extends CrudRepository<Attempt, Integer> {

	List<Attempt> findByResultListId(Integer resultId);

	List<Attempt> findByResultListIdAndQuestionListId(Integer resultId, Integer questionId);

}
