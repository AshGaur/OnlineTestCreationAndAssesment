package com.testcreation.trainer.dao;


import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.testcreation.trainer.bean.Trainer;

@Repository
public interface TrainerRepository extends CrudRepository<Trainer, Integer> {

	List<Trainer> findBySubscriptionId(Integer id);
}
