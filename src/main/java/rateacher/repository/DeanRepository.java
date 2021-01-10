package rateacher.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import rateacher.model.College;
import rateacher.model.Dean;
import rateacher.model.Teacher;

public interface DeanRepository extends Repository<Dean, Integer>{
	
	Collection<Dean> findAll();
	
	@Query("select c from College c")
	Collection<College> findAllColleges();
}
