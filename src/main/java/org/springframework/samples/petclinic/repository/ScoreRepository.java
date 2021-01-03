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
	
	@Query("select s from Teacher s where s.id = ?1")
	Collection<Score> findAllByTeacherId(int id) throws DataAccessException;


	@SuppressWarnings("unchecked")
	Score save(@Valid Score score) throws DataAccessException;

	@Query("select s from Score s where s.teacher.id = ?1")
	Collection<Score> findAllScoreByTeacherId(int id) throws DataAccessException;
	
	void delete(@Valid Score score);


}
