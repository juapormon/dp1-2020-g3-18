package rateacher.tests.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import rateacher.model.College;
import rateacher.model.Teacher;
import rateacher.service.CollegeService;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))

public class CollegeServiceTest {
	
	@Autowired
	protected CollegeService collegeService;
	
	//Positive test
	@Test
	@DisplayName("Find college by Id")
	public void testFindCollegeById() {
		College college = this.collegeService.findCollegeById(1);
		Assertions.assertThat(college!=null);
		
	}
	
	
	
	
	//Negative test
	@Test
	@DisplayName("Find college by bad Id")
	public void testFindCollegeByBadId() {
		int wrongId = 1985;
		College college = this.collegeService.findCollegeById(wrongId);
		Assertions.assertThat(college==null);
		
	}
	
	//Possitive test
	@Test
	@DisplayName("Save college")
	public void testStaveCollege() {
		Teacher t1 = new Teacher();
		Collection<College> lc1 = this.collegeService.findAllColleges();
		Collection<College> lc2 = this.collegeService.findAllColleges();

		List<Teacher>lt = new ArrayList<>();
		lt.add(t1);
		College college = new College("UPO", "Sevilla", lt);
		lc2.add(college);
		Assertions.assertThat(college!=null);
		Assertions.assertThat(lc1.size()==lc2.size()+1);
	}

	//Negative test
	@Test
	@DisplayName("Save college with empty name")
	public void testSaveCollegeBadName() {
		Teacher t1 = new Teacher();
		List<Teacher>lt = new ArrayList<>();
		lt.add(t1);
		Collection<College> lc1 = this.collegeService.findAllColleges();
		Collection<College> lc2 = this.collegeService.findAllColleges();
		int size1 = lc1.size();
		int size2 = lc2.size();
		
		College college = new College("", "Madrid", lt);
		lc2.add(college);
		
		Assertions.assertThat(college==null);
		Assertions.assertThat(size1==size2);
		
	}
	//Negative test
	@Test
	@DisplayName("Save college with empty city name")
	public void testSaveCollegeBadCity() {
		Teacher t1 = new Teacher();
		List<Teacher>lt = new ArrayList<>();
		lt.add(t1);
		Collection<College> lc1 = this.collegeService.findAllColleges();
		Collection<College> lc2 = this.collegeService.findAllColleges();
		int size1 = lc1.size();
		int size2 = lc2.size();
		
		College college = new College("UPO", "", lt);
		lc2.add(college);
		
		Assertions.assertThat(college==null);
		Assertions.assertThat(size1==size2);
		
	}
	
	
	//Positive test
	@Test
	@DisplayName("Test find all colleges")
	public void testFinAllColleges() {
		Collection<College> lc = this.collegeService.findAllColleges();
		Assertions.assertThat(lc.size()>0);
		

	}
	

}
