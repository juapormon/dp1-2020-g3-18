package rateacher.tests.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import rateacher.model.College;
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
}
