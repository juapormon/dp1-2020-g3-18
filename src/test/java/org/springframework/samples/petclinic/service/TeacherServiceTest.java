package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Teacher;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javassist.bytecode.stackmap.BasicBlock.Catch;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class TeacherServiceTest {

	
	@Autowired
	protected TeacherService teacherService; //= new TeacherService(repo);


	
	//Casos positivos
	@Test
	@DisplayName("Finding all teachers")
	void testFindAll() {

		Collection<Teacher> res = this.teacherService.findTeachers();

		assertTrue(!res.isEmpty());
		assertTrue(res.size()==2);

	}
	@Test
	@DisplayName("Finding teacher by id")
	void testFindTeacherById(){
		
		Teacher teacher = this.teacherService.findTeacherById(1);
		System.out.println(teacher.getFirstName());
		assertThat(teacher.getFirstName()).startsWith("Julián");
	}
	
	//Positive test
	@Test
	@Transactional
	public void shouldSaveTeacher() {
		Collection<Teacher> teachers = this.teacherService.findTeachers();
		int found = teachers.size();

		Teacher teacher = new Teacher();
		teacher.setFirstName("Francisco");
		teacher.setLastName("Fernández");
                User user=new User();
                user.setUsername("Fran");
                user.setPassword("francisco1");
                user.setEnabled(true);
                teacher.setUser(user);                
                
		this.teacherService.saveTeacher(teacher);
		assertThat(teacher.getId().longValue()).isNotEqualTo(0);

		teachers = this.teacherService.findTeachers();
		assertThat(teachers.size()).isEqualTo(found + 1);
	}
	
	
	// Negative test
	/*@Test
	@Transactional
	public void shouldSaveTeacherNegative() {
		Collection<Teacher> teachers = this.teacherService.findTeachers();
		int found = teachers.size();

		Teacher teacher = new Teacher();
		teacher.setFirstName("Pepe");
		teacher.setLastName("García");
                User user=new User();
                user.setUsername("pepe");
                user.setPassword("pepe1");
                user.setEnabled(true);
                teacher.setUser(user);                
                
			assertThat(teacher.getId().longValue()).isEqualTo(0);
		teachers = this.teacherService.findTeachers();
		assertThat(teachers.size()).isEqualTo(found + 1);
	}*/


}