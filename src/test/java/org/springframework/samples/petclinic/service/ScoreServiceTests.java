package org.springframework.samples.petclinic.service;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Score;
import org.springframework.samples.petclinic.model.Student;
import org.springframework.samples.petclinic.model.Teacher;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.ScoreRepository;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ScoreServiceTests {
	
	ScoreRepository repo = mock(ScoreRepository.class);
	
	ScoreService scoreService = new ScoreService(repo);
	
	Score score1;
	Score score2;
	

	
	
//	@BeforeEach
//	void initAll() {
//		score1 = new Score(7, "buen profesor", new Teacher());
//	}

	@BeforeEach
	void initAll() {
		score1 = new Score(7, "buen profesor",new Student(), new Teacher());
	}

	
	//Casos positivos
	@Test
	@DisplayName("Finding all scores")
	void testFindAll() throws DataAccessException{

		Collection<Score> res;

		when(this.repo.findAll()).thenReturn(Lists.list(score1, score2));

		res = this.scoreService.findAll();

		assertTrue(!res.isEmpty());
		assertTrue(res.size()==2);

	}
	
	@Test
	@DisplayName("find by id returns score")
	void testFindbyId() throws DataAccessException {
		int theId = 8;
		score1.setId(theId);
		when(repo.findById(8)).thenReturn(score1);
		Score res = scoreService.findScoreById(theId);
		assertTrue(res.getValu()==score1.getValu());
	}
	
	@Test
	@Transactional
	@DisplayName("Saving a score works fine")
	public void shouldSaveScore() {
	
		Collection<Score> scores = this.scoreService.findAll();
		int found = scores.size();

		Score nuevoScore = new Score(9, "se porta regular en la clase, explica ",
				new Student(), new Teacher());         
		nuevoScore.setId(13);
                
		this.scoreService.saveScore(nuevoScore);
		
		Mockito.verify(this.repo, Mockito.times(1)).save(nuevoScore);
		
//		scores = this.scoreService.findAll();
//		assertThat(scores.size()).isEqualTo(found + 1);
	}

	//Casos negativos
	@Test
	@DisplayName("find by id doesn't exists ")
	void testFindbybadId() throws DataAccessException {
		int badId=3484;
		when(repo.findById(badId)).thenReturn(null);
		Score scor = scoreService.findScoreById(badId);	
		assertTrue(scor==null);
	}
	
	@Test
	@Transactional
	@DisplayName("Saving a bad formed score return exception")
	public void shouldNotSaveScore() {
	
		Collection<Score> scores = this.scoreService.findAll();
		int found = scores.size();

		Score nuevoScore = new Score(13, "se porta regular en la clase, explica ",
				new Student(), new Teacher());         
		nuevoScore.setId(13);
                
		this.scoreService.saveScore(nuevoScore);

		scores = this.scoreService.findAll();
		Mockito.verify(this.repo, Mockito.times(0)).save(nuevoScore);;
	}
}


