package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Score;

public interface ScoreRepository extends Repository<Score, Integer>{

	Score findById(int id) throws DataAccessException;
	
	Collection<Score> findAll() throws DataAccessException;
}