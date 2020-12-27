package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Department;
import org.springframework.samples.petclinic.model.Score;
import org.springframework.samples.petclinic.model.Student;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Authorities;
import org.springframework.samples.petclinic.model.Department;

import org.springframework.samples.petclinic.model.Teacher;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.AuthoritiesRepository;
import org.springframework.samples.petclinic.repository.ScoreRepository;
import org.springframework.samples.petclinic.repository.TeacherRepository;
import org.springframework.samples.petclinic.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class TeacherServiceTest {

	@Autowired
	//protected TeacherService teacherService; // = new TeacherService(repo);
	
	
	ScoreRepository scoreRepo = mock(ScoreRepository.class);
	
	ScoreService scoreService = new ScoreService(scoreRepo);
	
	UserRepository userRepo = mock(UserRepository.class);
	
	UserService userService = new UserService(userRepo);
	
	AuthoritiesRepository authoritiesRepo = mock(AuthoritiesRepository.class);
	
	 AuthoritiesService authoritiesService = new AuthoritiesService(authoritiesRepo, userService);
	
	TeacherRepository teacherRepo = mock(TeacherRepository.class);
	
	TeacherService teacherService = new TeacherService(teacherRepo, userService, authoritiesService);
	
	Teacher t1;
	Teacher t2;

	@Autowired
	protected StudentService studentService; // = new TeacherService(repo);

	@Autowired
	protected DepartmentService departmentService;
	
	// Casos positivos
	@BeforeEach
	void initAll() {
		t1 = new Teacher();
		t1.setName("Perico");
		t1.setLastName("Pedrolo");
		t1.setUser(new User());
		
//		Collection<College> colleges = new ArrayList<College>();
		
//		College college1 = new College();
//		college1.setCity("Sevilla");
//		college1.setName("US");
//		colleges.add(college1);
		
//		t1.setColleges(colleges);
		
		Collection<Department> departments = new ArrayList<Department>();
		
		Department department1 = new Department();
		department1.setName("Math");
		departments.add(department1);
		
		t1.setDepartments(departments);
	}
	
//	@Test
//	@DisplayName("Finding all teachers")
//	void testFindAll() throws DataAccessException{
//
//		Collection<Teacher> res;
//
//		when(this.teacherRepo.findAll()).thenReturn(Lists.list(t1, t2));
//
//		res = this.teacherService.findTeachers();
//
//		assertTrue(!res.isEmpty());
//		assertTrue(res.size()==2);
//
//	}
//
//	@Test
//	@DisplayName("Finding teacher by id")
//	void testFindTeacherById() {
//
//		Teacher teacher = this.teacherService.findTeacherById(1);
//		System.out.println(teacher.getFirstName());
//		assertThat(teacher.getFirstName()).startsWith("Julian");
//	}
//
//	@Test
//	@DisplayName("Finding teacher by firstName")
//	void testFindTeacherByFirstName() {
//
//		List<Teacher> teacher = this.teacherService.findTeacherByFirstName("Julian");
//		assertThat(teacher.size() == 1);
//		assertThat(teacher.get(0).getId() == 1);
//	}
//
//	@Test
//	@DisplayName("Finding Teacher With Score")
//	void testShowTeacherWithScore() {
//
//		List<Teacher> teacher = (List<Teacher>) this.teacherService.showTeacherWithScore();
//		assertThat(teacher.size() == 1);
//		assertThat(teacher.get(0).getId() == 1);
//	}
//
//	@Test
//	@DisplayName("Finding Scores by Teacher Id")
//	void testfindBySubject() {
//		int id = 1;
//		List<Score> scores = (List<Score>) this.teacherService.findScoresByTeacherId(id);
//		assertThat(scores.size() == 1);
//		assertThat(scores.get(0).getId() == 1);
//	}
//
//	@Test
//	@DisplayName("Finding Teacher by Department")
//	void findTeachersFromDepartment() {
//		int id = 5;
//		Department d = departmentService.findDepartmentById(id);
//		List<Teacher> teachers = (List<Teacher>) teacherService.findTeachers();
//		assertThat(teachers.size() == 1);
//		List<Teacher> result = new ArrayList<Teacher>();
//		for(Teacher t2 : teachers) {
//			if(t2.getDepartments().contains(d)) 
//				result.add(t2);
//		}
//		assertThat(result.size() == 1);
//		assertThat(result.get(0).getId() == 1);
//
//	}
//
//	// Positive test
//	@Test
//	@Transactional
//	public void shouldSaveTeacher() {
//		Collection<Teacher> teachers = this.teacherService.findTeachers();
//		int found = teachers.size();
//
//		Teacher teacher = new Teacher();
//		teacher.setName("Francisco");
//		teacher.setFirstName("Francisco");
//		teacher.setLastName("Fernández");
//		User user = new User();
//		user.setUsername("Fran");
//		user.setPassword("francisco1");
//		user.setEnabled(true);
//		teacher.setUser(user);
//
//		this.teacherService.saveTeacher(teacher);
//		assertThat(teacher.getId().longValue()).isNotEqualTo(0);
//
//		teachers = this.teacherService.findTeachers();
//		assertThat(teachers.size()).isEqualTo(found + 1);
//	}
//
//	// Negative test
//
//	@Test
//	@DisplayName("Finding a Teacher by bad id")
//	void testFindTeacherByBadId() {
//		int badId = 234234;
//		assertThrows(AssertionError.class, () -> this.teacherService.findTeacherById(badId));
//	}

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

//}
	
	@Test
	@DisplayName("Find by id returns teacher")
	void testFindbyId() throws DataAccessException {
		int id = 8;
		t1.setId(id);
		when(teacherRepo.findById(8)).thenReturn(t1);
		
		ArrayList<Department> deps = (ArrayList<Department>) t1.getDepartments();
		Department d1 = deps.get(0);
		
		Teacher res = teacherService.findTeacherById(id);
		
		assertTrue(res.getName()==t1.getName());
		assertTrue(d1.getName().equals("Math"));
	}

//	@Test
//	@Transactional
//	@DisplayName("Saving a teacher works fine")
//	public void shouldSaveTeacher() {
	
//		Este no funciona ni siguiendo el consejo del profesor. PArece dser culpa del framework porque no encuentra un usuario ç
//		temporal que él mismo crea para uno de sus métodos
		
//		User newUser = new User();
//		
//		newUser.setUsername("lolailola");
//		newUser.setEnabled(true);
//		newUser.setPassword("passdelola");
//		
//		Authorities authority = new Authorities();
//		Set<Authorities> au = new HashSet<Authorities>();
//		au.add(authority);
//		
//		newUser.setAuthorities(au);
//		
//		Teacher newTeacher = new Teacher();
//		
//		newTeacher.setUser(newUser);		
//		newTeacher.setName("Lola");
//		newTeacher.setLastName("Lolailo");
//		newTeacher.setId(69);
//
//		this.teacherService.saveTeacher(newTeacher);
//		
//		Mockito.verify(this.teacherRepo, Mockito.times(1)).save(newTeacher);
//	}

//	@Autowired
//	protected TeacherService teacherService; //= new TeacherService(repo);
//	
//	@Autowired
//	protected StudentService studentService; //= new TeacherService(repo);
//
//
//	
//	//Casos positivos
//	@Test
//	@DisplayName("Finding all teachers")
//	void testFindAll() {
//
//		Collection<Teacher> res = this.teacherService.findTeachers();
//
//		assertTrue(!res.isEmpty());
//
//	}
//	@Test
//	@DisplayName("Finding teacher by id")
//	void testFindTeacherById(){
//		
//		Teacher teacher = this.teacherService.findTeacherById(1);
//		System.out.println(teacher.getFirstName());
//		assertThat(teacher.getFirstName()).startsWith("Julian");
//	}
//	
//	@Test
//	@DisplayName("Finding teacher by firstName")
//	void testFindTeacherByFirstName(){
//		
//		List<Teacher> teacher = this.teacherService.findTeacherByFirstName("Julian");
//		assertThat(teacher.size()==1);
//		assertThat(teacher.get(0).getId()==1);
//	}
//	
//	//Positive test
//	@Test
//	@Transactional
//	public void shouldSaveTeacher() {
//		Collection<Teacher> teachers = this.teacherService.findTeachers();
//		int found = teachers.size();
//
//		Teacher teacher = new Teacher();
//		teacher.setName("Francisco");
//		teacher.setFirstName("Francisco");
//		teacher.setLastName("Fernández");
//                User user=new User();
//                user.setUsername("Fran");
//                user.setPassword("francisco1");
//                user.setEnabled(true);
//                teacher.setUser(user);                
//                
//		this.teacherService.saveTeacher(teacher);
//		assertThat(teacher.getId().longValue()).isNotEqualTo(0);
//
//		teachers = this.teacherService.findTeachers();
//		assertThat(teachers.size()).isEqualTo(found + 1);
//	}
//	
//
//	
//	// Negative test
//	
//	@Test
//	@DisplayName("Finding a Teacher by bad id")
//	void testFindTeacherByBadId(){
//		int badId = 234234;
//		assertThrows(AssertionError.class,()->this.teacherService.findTeacherById(badId));
//	}
//	/*@Test
//	@Transactional
//	public void shouldSaveTeacherNegative() {
//		Collection<Teacher> teachers = this.teacherService.findTeachers();
//		int found = teachers.size();
//
//		Teacher teacher = new Teacher();
//		teacher.setFirstName("Pepe");
//		teacher.setLastName("García");
//                User user=new User();
//                user.setUsername("pepe");
//                user.setPassword("pepe1");
//                user.setEnabled(true);
//                teacher.setUser(user);                
//                
//			assertThat(teacher.getId().longValue()).isEqualTo(0);
//		teachers = this.teacherService.findTeachers();
//		assertThat(teachers.size()).isEqualTo(found + 1);
//	}*/

}
