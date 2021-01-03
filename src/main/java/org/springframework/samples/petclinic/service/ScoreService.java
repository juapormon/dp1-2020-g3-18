package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Score;
import org.springframework.samples.petclinic.model.Subject;
import org.springframework.samples.petclinic.repository.ScoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScoreService {
	
	private ScoreRepository scoreRepository;

	@Autowired
	public ScoreService(ScoreRepository scoreRepository) {
		this.scoreRepository = scoreRepository;
	}
	
	@Transactional(readOnly = true)	
	public Collection<Score> findScores() throws DataAccessException {
		return scoreRepository.findAll();
	}
	
	@Transactional(readOnly = true)	
	public Optional<Score> findScoreById(int id) throws DataAccessException {
		return scoreRepository.findById(id);
	}
	
	
	@Transactional(readOnly = true)	
	public void saveScore(@Valid Score score) throws DataAccessException  {
		scoreRepository.save(score);
	}
	
	public void delete(Score score) {
		scoreRepository.delete(score);		
	}
}
