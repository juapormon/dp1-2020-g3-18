package rateacher.repository;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;

import rateacher.model.TeachingPlan;

public interface TeachingPlanRepository  extends Repository<TeachingPlan, Integer>{
	
	TeachingPlan findById(int id) throws DataAccessException;

	Collection<TeachingPlan> findAll() throws DataAccessException;

	void save(@Valid TeachingPlan teachingPlan) throws DataAccessException;
	
	void delete(@Valid TeachingPlan teachingPlan);

}
