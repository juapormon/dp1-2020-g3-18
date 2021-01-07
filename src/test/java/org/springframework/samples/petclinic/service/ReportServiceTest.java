package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collection;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Report;
import org.springframework.samples.petclinic.model.Score;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.ReportRepository;
import org.springframework.samples.petclinic.repository.ScoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javassist.bytecode.stackmap.BasicBlock.Catch;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ReportServiceTest {

	
	@Autowired
	protected ReportService reportService; 
	
	@Autowired
	protected ScoreService scoreService; 
	
	//Casos positivos
	@Test
	@DisplayName("Finding all reports")
	void testFindAll() {

		Collection<Report> res = this.reportService.findReports();

		assertTrue(!res.isEmpty());

	}
	@Test
	@DisplayName("Finding report by id")
	void testFindReportById(){
		
		Report report = this.reportService.findReportById(5);
		System.out.println(report.getScore().getComment());
		assertThat(report.getScore().getComment()).startsWith("Las clases de este profesor");
	}
	
	@Test
	@Transactional
	@DisplayName("Saving a report works fine")
	public void shouldSaveReport() {
	
		Collection<Report> reports = this.reportService.findReports();
		int found = reports.size();

		Report report = new Report();
		Score score = new Score();
		score.setValu(3);
		score.setComment("Es respetuoso y correcto pero explica que dan ganas de quitarse de su asignatura");          
               
		report.setScore(score);
		report.setReason("Me hiere los sentimientos");
		this.scoreService.saveScore(score);
		this.reportService.saveReport(report);
		assertThat(report.getId().longValue()).isNotEqualTo(0);

		reports = this.reportService.findReports();
		assertThat(reports.size()).isEqualTo(found + 1);
	}

	
	// Casos negativos
	@Test
	@DisplayName("Finding a report by bad id")
	void testFindReportByBadId(){
		int badId = 223323533;
		assertThrows(AssertionError.class,()->this.reportService.findReportById(badId));
	}
	
	@Test
	@Transactional
	@DisplayName("Saving a report that lacks a reason")
	public void SavingReportBadTest() {
	
		Report report = new Report();
		report.setReason(null);
		Score score = new Score();
		score.setValu(3);
		score.setComment("Es extremadamente insoportable");
		report.setScore(score);
                
		assertThrows(ConstraintViolationException.class,()->this.reportService.saveReport(report));
	}
	


}
