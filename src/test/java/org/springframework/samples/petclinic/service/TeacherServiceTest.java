package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Department;
import org.springframework.samples.petclinic.model.Score;
import org.springframework.samples.petclinic.model.Student;
import org.springframework.samples.petclinic.model.Teacher;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javassist.bytecode.stackmap.BasicBlock.Catch;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class TeacherServiceTest {

	@Autowired
	protected TeacherService teacherService; // = new TeacherService(repo);

	@Autowired
	protected StudentService studentService; // = new TeacherService(repo);

	@Autowired
	protected DepartmentService departmentService;
	
	// Casos positivos
	@Test
	@DisplayName("Finding all teachers")
	void testFindAll() {

		Collection<Teacher> res = this.teacherService.findTeachers();

		assertTrue(!res.isEmpty());

	}

	@Test
	@DisplayName("Finding teacher by id")
	void testFindTeacherById() {

		Teacher teacher = this.teacherService.findTeacherById(1);
		System.out.println(teacher.getFirstName());
		assertThat(teacher.getFirstName()).startsWith("Julian");
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

	// Positive test
	@Test
	@Transactional
	public void shouldSaveTeacher() {
		Collection<Teacher> teachers = this.teacherService.findTeachers();
		int found = teachers.size();

		Teacher teacher = new Teacher();
		teacher.setName("Francisco");
		teacher.setFirstName("Francisco");
		teacher.setLastName("Fernández");
		User user = new User();
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

	@Test
	@DisplayName("Finding a Teacher by bad id")
	void testFindTeacherByBadId() {
		int badId = 234234;
		assertThrows(AssertionError.class, () -> this.teacherService.findTeacherById(badId));
	}

	/*
	 * @Test
	 * 
	 * @Transactional public void shouldSaveTeacherNegative() { Collection<Teacher>
	 * teachers = this.teacherService.findTeachers(); int found = teachers.size();
	 * 
	 * Teacher teacher = new Teacher(); teacher.setFirstName("Pepe");
	 * teacher.setLastName("García"); User user=new User();
	 * user.setUsername("pepe"); user.setPassword("pepe1"); user.setEnabled(true);
	 * teacher.setUser(user);
	 * 
	 * assertThat(teacher.getId().longValue()).isEqualTo(0); teachers =
	 * this.teacherService.findTeachers();
	 * assertThat(teachers.size()).isEqualTo(found + 1); }
	 */

}
