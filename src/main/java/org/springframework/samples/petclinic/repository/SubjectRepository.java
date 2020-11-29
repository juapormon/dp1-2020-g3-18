package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Subject;

public interface SubjectRepository extends Repository<Subject, Integer>{
	
	Subject findById(int id) throws DataAccessException;
	
	Collection<Subject> findAll() throws DataAccessException;

	void save(@Valid Subject subject) throws DataAccessException;

}
