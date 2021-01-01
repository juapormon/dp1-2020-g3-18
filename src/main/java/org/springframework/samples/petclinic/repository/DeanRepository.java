package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.College;
import org.springframework.samples.petclinic.model.Dean;
import org.springframework.samples.petclinic.model.Teacher;

public interface DeanRepository extends Repository<Dean, Integer>{
	
	Collection<Dean> findAll();
	
	@Query("select c from College c")
	Collection<College> findAllColleges();
}
