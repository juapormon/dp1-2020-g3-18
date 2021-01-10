package rateacher.tests.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import java.util.Set;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Department;
import org.springframework.samples.petclinic.model.Score;
import org.springframework.samples.petclinic.model.Student;
import org.springframework.samples.petclinic.model.Teacher;
import org.springframework.samples.petclinic.model.User;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rateacher.model.Department;
import rateacher.model.Score;
import rateacher.model.Student;
import rateacher.model.Teacher;
import rateacher.model.User;
import rateacher.service.DepartmentService;
import rateacher.service.StudentService;
import rateacher.service.TeacherService;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class TeacherServiceTest {
	
	@Autowired
	protected StudentService studentService; 
	
	@Autowired
	protected TeacherService teacherService; 
	
	@Autowired
	protected DepartmentService departmentService; 
	
	@Test
	@DisplayName("Finding teacher by id")
	void testFindTeacherById() {

		Teacher teacher = this.teacherService.findTeacherById(1);
		System.out.println(teacher.getFirstName());
		assertThat(teacher.getFirstName()).startsWith("Julian");
	}
	//Test positivo 
		@Test
		@DisplayName("Finding teachers by student id")
		void testFindTeacherByStudentId() {
			
			Collection<Teacher> ratedTeachers = this.teacherService.findTeacherByStudentId(1);
			Integer size = ratedTeachers.size();
			
			assertTrue(size != null);
			assertTrue(ratedTeachers!= null);
		}
		
		//Test negativo
		@Test
		@DisplayName("Finding teachers by bad student id")

		void testFindTeachersByBadStudentId() {
			Integer wrongStudentId = 1991;
			assertThrows(AssertionError.class, () -> this.teacherService.findTeacherByStudentId(wrongStudentId));
		}

	@Test
	@DisplayName("Finding teacher by firstName")
	void testFindTeacherByFirstName() {

		List<Teacher> teacher = this.teacherService.findTeacherByFirstName("Julian");
		assertThat(teacher.size() == 1);
		assertThat(teacher.get(0).getId() == 1);
	}

	@Test
	@DisplayName("Finding Teacher With Score")
	void testShowTeacherWithScore() {

		List<Teacher> teacher = (List<Teacher>) this.teacherService.showTeacherWithScore();
		assertThat(teacher.size() == 1);
		assertThat(teacher.get(0).getId() == 1);
	}

	@Test
	@DisplayName("Finding Scores by Teacher Id")
	void testfindBySubject() {
		int id = 1;
		List<Score> scores = (List<Score>) this.teacherService.findScoresByTeacherId(id);
		assertThat(scores.size() == 1);
		assertThat(scores.get(0).getId() == 1);
	}

	@Test
	@DisplayName("Finding Teacher by Department")
	void findTeachersFromDepartment() {
		int id = 5;
		Department d = departmentService.findDepartmentById(id);
		List<Teacher> teachers = (List<Teacher>) teacherService.findTeachers();
		assertThat(teachers.size() == 1);
		List<Teacher> result = new ArrayList<Teacher>();
		for(Teacher t2 : teachers) {
			if(t2.getDepartments().contains(d)) 
				result.add(t2);
		}
		assertThat(result.size() == 1);
		assertThat(result.get(0).getId() == 1);

	}
	// Negative test

	@Test
	@DisplayName("Finding a Teacher by bad id")
	void testFindTeacherByBadId() {
		int badId = 234234;
		assertThrows(AssertionError.class, () -> this.teacherService.findTeacherById(badId));
	}

	// Negative test
	
 	@Test
	@DisplayName("Finding teacher by student id")
	void testFindByStudentId(){
		
		List<Teacher> teacher = (List<Teacher>)this.teacherService.findTeacherByStudentId(1);
		
		assertThat(teacher.get(0).getFirstName()).startsWith("Julian");
		assertThat(teacher.get(1).getFirstName()).startsWith("Popench");
	}
	
	//Casos positivos
	@Test
	@DisplayName("Finding all teachers")
	void testFindAll2() {

		Collection<Teacher> res = this.teacherService.findTeachers();

		assertTrue(!res.isEmpty());

	}
}

