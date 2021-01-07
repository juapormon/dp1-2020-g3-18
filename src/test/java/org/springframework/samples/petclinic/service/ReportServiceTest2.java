package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Collection;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.samples.petclinic.model.Report;
import org.springframework.samples.petclinic.model.Score;
import org.springframework.samples.petclinic.repository.ReportRepository;
import org.springframework.samples.petclinic.repository.ScoreRepository;
import org.springframework.samples.petclinic.repository.StudentRepository;
import org.springframework.samples.petclinic.repository.TeacherRepository;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ReportServiceTest2 {
	

	
	ReportRepository reportRepo = mock(ReportRepository.class);
	
	ReportService reportService = new ReportService(reportRepo);

	TeacherRepository teacherRepo = mock(TeacherRepository.class);
	
	TeacherService teacherService = new TeacherService(teacherRepo);
	
	StudentRepository studentRepo = mock(StudentRepository.class);
	
	StudentService studentService = new StudentService(studentRepo, teacherRepo);
	
	ScoreRepository scoreRepo = mock(ScoreRepository.class);
	
	ScoreService scoreService = new ScoreService(scoreRepo, studentService);
	
	Report r1;
	Report r2;
	Score s1;
	Score s2;
	
	// Casos positivos
	@BeforeEach
	void initAll() {
		r1 = new Report();
		r1.setReason("Me ofende");
		r1.setScore(s1);
		
		r2 = new Report();
		r2.setReason("No me gusta");
		r2.setScore(s1);
	}
	
	@Test
	@DisplayName("Finding all reports")
	void testFindAll() {

		Collection<Report> res;

		when(this.reportRepo.findAll()).thenReturn(Lists.list(r1, r2));

		res = this.reportService.findReports();

		assertTrue(!res.isEmpty());
		assertTrue(res.size()==2);
	}
	@Test
	@DisplayName("Finding report by id")
	void testFindReportById(){
		int id = 4;
		r1.setId(id);
		when(reportRepo.findById(id)).thenReturn(r1);		
		Report res = reportService.findReportById(id);
		assertTrue(res.getReason().startsWith("Me"));
	}
	// Casos negativos

	@Test
	@DisplayName("Finding a Report by bad id")
	void testFindreportByBadId(){
		int badId = 97856;
		assertThrows(AssertionError.class,()->this.reportService.findReportById(badId));
	}
}
