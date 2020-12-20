package org.springframework.samples.petclinic.service;


import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Score;
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
	public Score findScoreById(int id){
		return scoreRepository.findById(id);
	}
	
	@Transactional(readOnly = true)	
	public Collection<Score> findAll(){
		return scoreRepository.findAll();
	}
	
	
	@Transactional()	
	public void saveScore(@Valid Score score)  {
		scoreRepository.save(score);
	}	
	
	@Transactional(readOnly = true)	
	public void removeScore(@Valid Score score){
		scoreRepository.delete(score);
	}
}
