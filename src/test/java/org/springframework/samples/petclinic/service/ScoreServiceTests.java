package org.springframework.samples.petclinic.service;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Score;
import org.springframework.samples.petclinic.model.Teacher;
import org.springframework.samples.petclinic.repository.ScoreRepository;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collection;


import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@SpringBootTest
public class ScoreServiceTests {
	
	ScoreRepository repo = mock(ScoreRepository.class);
	
	ScoreService scoreService = new ScoreService(repo);
	
	Score score1;
	Score score2;
	
	@BeforeEach
//	void initAll() {
//		score1 = new Score(7, "buen profesor", new Teacher());
//	}
	
	//Casos positivos
	@Test
	@DisplayName("Finding all scores")
	void testFindAll() throws DataAccessException{

		Collection<Score> res;

		when(this.repo.findAll()).thenReturn(Lists.list(score1, score2));

		res = this.scoreService.findScores();

		assertTrue(!res.isEmpty());
		assertTrue(res.size()==2);

	}
//	
//	@Test
//	@DisplayName("find by id returns score")
//	void testFindbyId() throws DataAccessException {
//		int theId = 8;
//		score1.setId(theId);
//		when(repo.findById(8)).thenReturn(score1);
//		Score res = scoreService.findScoreById(theId);
//		assertTrue(res.getPoint()==score1.getPoint());
//	}
////	assert(scoreRepository.findAll().contains(score));
//
//	//Casos negativos
//	@Test
//	@DisplayName("find by id doesn't exists ")
//	void testFindbybadId() throws DataAccessException {
//		int badId=3484;
//		when(repo.findById(badId)).thenThrow(DataAccessException.class);
//		assertThrows(DataAccessException.class,()->scoreService.findScoreById(badId));
//	}	

}
