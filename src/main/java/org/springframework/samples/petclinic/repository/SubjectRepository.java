package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Subject;

public interface SubjectRepository extends CrudRepository<Subject, Integer>{
	
	Optional<Subject> findById(int id) throws DataAccessException;
	
	Collection<Subject> findAll() throws DataAccessException;
	
	@SuppressWarnings("unchecked")
	Subject save(@Valid Subject entity) throws DataAccessException;

}
