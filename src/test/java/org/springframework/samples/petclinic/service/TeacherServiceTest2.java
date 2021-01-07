package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;


import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Department;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Teacher;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.AuthoritiesRepository;
import org.springframework.samples.petclinic.repository.DepartmentRepository;
import org.springframework.samples.petclinic.repository.ScoreRepository;
import org.springframework.samples.petclinic.repository.StudentRepository;
import org.springframework.samples.petclinic.repository.TeacherRepository;
import org.springframework.samples.petclinic.repository.UserRepository;
import org.springframework.stereotype.Service;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class TeacherServiceTest2 {
	
	ScoreRepository scoreRepo = mock(ScoreRepository.class);
	
	ScoreService scoreService = new ScoreService(scoreRepo);
	
	UserRepository userRepo = mock(UserRepository.class);
	
	UserService userService = new UserService(userRepo);
	
	AuthoritiesRepository authoritiesRepo = mock(AuthoritiesRepository.class);
	
	 AuthoritiesService authoritiesService = new AuthoritiesService(authoritiesRepo, userService);
	
	TeacherRepository teacherRepo = mock(TeacherRepository.class);
	
	TeacherService teacherService = new TeacherService(teacherRepo);
	
	StudentRepository studentRepo = mock(StudentRepository.class);
	
	StudentService studentService = new StudentService(studentRepo, teacherRepo);
	
	DepartmentRepository departmentRepo = mock(DepartmentRepository.class);
	
	DepartmentService departmentService = new DepartmentService(departmentRepo);
	
	
	Teacher t1;
	Teacher t2;
	
	// Casos positivos
	@BeforeEach
	void initAll() {
		t1 = new Teacher();
		t1.setName("Perico");
		t1.setLastName("Pedrolo");
		t1.setUser(new User());
		
		Collection<Department> departments = new ArrayList<Department>();
		
		Department department1 = new Department();
		department1.setName("Math");
		departments.add(department1);
		
		t1.setDepartments(departments);
	}
	
	@Test
	@DisplayName("Finding all teachers")
	void testFindAll() throws DataAccessException{

		Collection<Teacher> res;

		when(this.teacherRepo.findAll()).thenReturn(Lists.list(t1, t2));

		res = this.teacherService.findTeachers();

		assertTrue(!res.isEmpty());
		assertTrue(res.size()==2);

	}

	@Test
	@DisplayName("Find by id returns teacher")
	void testFindbyId() throws DataAccessException {
		int id = 8;
		t1.setId(id);
		when(teacherRepo.findById(id)).thenReturn(t1);
		
		ArrayList<Department> deps = (ArrayList<Department>) t1.getDepartments();
		Department d1 = deps.get(0);
		
		Teacher res = teacherService.findTeacherById(id);
		
		assertTrue(res.getName()==t1.getName());
		assertTrue(d1.getName().equals("Math"));
	}
	
	@Test
	@DisplayName("Finding a Teacher by bad id")
	void testFindTeacherByBadId(){
		int badId = 234234;
		assertThrows(AssertionError.class,()->this.teacherService.findTeacherById(badId));
	}
}
