package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Score;

public interface ScoreRepository extends CrudRepository<Score, Integer>{

	Optional<Score> findById(int id) throws DataAccessException;
	
	Collection<Score> findAll() throws DataAccessException;

	@SuppressWarnings("unchecked")
	Score save(@Valid Score score) throws DataAccessException;

}
