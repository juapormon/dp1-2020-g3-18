package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.College;

public interface CollegeRepository extends Repository<College, Integer>{
	
	Collection<College> findAll();
	
	College findById(int id);
	
	void save(@Valid College college) throws DataAccessException;
}

