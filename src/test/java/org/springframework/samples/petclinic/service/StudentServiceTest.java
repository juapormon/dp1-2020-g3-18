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
import org.springframework.samples.petclinic.model.Student;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javassist.bytecode.stackmap.BasicBlock.Catch;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class StudentServiceTest {

	
	@Autowired
	protected StudentService studentService; //= new StudentService(repo);


	
	//Casos positivos
	@Test
	@DisplayName("Finding all students")
	void testFindAll() {

		Collection<Student> res = this.studentService.findStudents();

		assertTrue(!res.isEmpty());
		assertTrue(res.size()==2);

	}
	@Test
	@DisplayName("Finding student by id")
	void testFindStudentById(){
		
		Student student = this.studentService.findStudentById(2);
		System.out.println(student.getFirstName());
		assertThat(student.getFirstName()).startsWith("Francisco");
	}
	
	//Positive test
	@Test
	@Transactional
	public void shouldSaveStudent() {
		Collection<Student> students = this.studentService.findStudents();
		int found = students.size();

		Student student = new Student();
		student.setFirstName("Paco");
		student.setLastName("Perez");
                User user=new User();
                user.setUsername("paco");
                user.setPassword("paco1");
                user.setEnabled(true);
                student.setUser(user);                
                
		this.studentService.saveStudent(student);
		assertThat(student.getId().longValue()).isNotEqualTo(0);

		students = this.studentService.findStudents();
		assertThat(students.size()).isEqualTo(found + 1);
	}
	
	
	// Negative test
	/*@Test
	@Transactional
	public void shouldSaveStudentNegative() {
		Collection<Student> students = this.studentService.findStudents();
		int found = students.size();

		Student student = new Student();
		student.setFirstName("Pepe");
		student.setLastName("García");
                User user=new User();
                user.setUsername("pepe");
                user.setPassword("pepe1");
                user.setEnabled(true);
                student.setUser(user);                
                
			assertThat(student.getId().longValue()).isEqualTo(0);
		students = this.studentService.findStudents();
		assertThat(students.size()).isEqualTo(found + 1);
	}*/


}
