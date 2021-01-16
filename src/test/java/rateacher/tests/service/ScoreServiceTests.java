package rateacher.tests.service;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import rateacher.model.Score;
import rateacher.model.Student;
import rateacher.model.Teacher;
import rateacher.model.User;
import rateacher.repository.ScoreRepository;
import rateacher.service.ScoreService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;


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
	
	ScoreService scoreService = new ScoreService(repo, null);
	
	Score score1;
	Score score2;
	
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
	@DisplayName("find scores by teacher id")
	void testFindAllScoresByTeacherId() throws DataAccessException {
		int teacherId = 8;
		Teacher t = new Teacher();
		t.setId(teacherId);
		Score sco1 = new Score();
		sco1.setTeacher(t);
		Score sco2 = new Score();
		sco2.setTeacher(t);
		Collection<Score> scores = new ArrayList<Score>();
		scores.add(sco1);
		scores.add(sco2);
		when(repo.findAllScoreByTeacherId(t.getId())).thenReturn(scores);
		Collection<Score> res = scoreService.findAllScoresByTeacherId(t.getId());
		assertTrue(res.size() == 2);
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
		Mockito.verify(this.repo, Mockito.times(0)).save(nuevoScore);//cambiar
	}
	
	
	@Test
	@DisplayName("find bad scores by teacher id")
	void testFindBadAllScoresByTeacherId() throws DataAccessException {
		int badTeacherId = 825;

		when(repo.findAllScoreByTeacherId(badTeacherId)).thenReturn(null);
		Collection<Score> res = scoreService.findAllScoresByTeacherId(badTeacherId);
		assertTrue(res == null);
	}
}


