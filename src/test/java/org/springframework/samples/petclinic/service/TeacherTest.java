package org.springframework.samples.petclinic.service;



import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.stereotype.Service;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class TeacherTest {
	
	@Autowired
	public TeacherService teacherService;
	
	@Test
	public void testTeacherName() {
		String name = teacherService.findTeacherById(1).getFirstName();
		String lastName = teacherService.findTeacherById(1).getLastName();
		assertEquals(name, "Julián");
		assertEquals(lastName, "Locuelo García");
	}
	
	@Test
	public void testTeacherUserName() {
		String username = teacherService.findTeacherById(1).getUser().getUsername();
		assertEquals(username, "juloga1");
	}


}
