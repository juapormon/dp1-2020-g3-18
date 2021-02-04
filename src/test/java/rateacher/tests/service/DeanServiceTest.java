package rateacher.tests.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;

import rateacher.model.College;
import rateacher.model.Dean;
import rateacher.model.PersonalExperience;
import rateacher.model.Teacher;
import rateacher.model.User;
import rateacher.repository.DeanRepository;
import rateacher.repository.TeacherRepository;
import rateacher.service.AuthoritiesService;
import rateacher.service.DeanService;
import rateacher.service.UserService;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class DeanServiceTest {
	
	private DeanRepository deanRepository=mock(DeanRepository.class);
	private TeacherRepository teacherRepository=mock(TeacherRepository.class);
	private UserService userService=mock(UserService.class);
	private AuthoritiesService authoritiesService=mock(AuthoritiesService.class);
	
	private DeanService deanService = new DeanService(deanRepository, teacherRepository, userService, authoritiesService) ;
	
	Dean dean;
	Collection<Dean> deans;
	@BeforeEach
	void setUp() {
		dean = new Dean(new User(), new College());
		deans = Lists.list(dean);
	}
	
	//POSITIVE
	@Test
	@DisplayName("Save Dean")
	void saveDeanTest() {
		//arrange
		User user = new User("manolo", "contraseño", true, null);
		Dean newDean = new Dean(user, new College());
		newDean.setId(130);
        //act
		this.deanService.saveDean(newDean);
		//assert
		Mockito.verify(this.deanRepository, Mockito.times(1)).save(newDean);
		Mockito.verify(this.userService, Mockito.times(1)).saveUser(newDean.getUser());
	}
	
	@Test
	@DisplayName("Save Teacher")
	void saveTeacherTest() {
		//arrange
		Teacher newTeacher = new Teacher("manuel", new User(), new ArrayList<>(), new PersonalExperience(), new ArrayList<>(), new ArrayList<>());
		newTeacher.setId(130);
        //act
		this.deanService.saveTeacher(newTeacher);
		//assert
		Mockito.verify(this.teacherRepository, Mockito.times(1)).save(newTeacher);
	}
	
	@Test
	@DisplayName("Finding all deans")
	void testFindAll() throws DataAccessException{
		//arrange
		Collection<Dean> res;

		when(this.deanRepository.findAll()).thenReturn(Lists.list(dean));
		//act
		res = this.deanService.findAll();
		//assert
		assertTrue(!res.isEmpty());
		assertTrue(res.size()==1);
	}
	
	@Test
	@DisplayName("Finding all colleges")
	void testFindAllColleges() throws DataAccessException{
		//arrange
		Collection<College> res;
		College college = new College("pan", "arroz", new ArrayList<>());
		
		when(this.deanRepository.findAllColleges()).thenReturn(Lists.list(college));
		//act
		res = this.deanService.findAllColleges();
		//assert
		assertTrue(!res.isEmpty());
		assertTrue(res.size()==1);
	}
	
	@Test
	@DisplayName("Finding dean by username")
	void testFindDeanByUsername() throws DataAccessException{
		//arrange
		Dean res;
		dean.setFirstName("depredadoor");
		when(this.deanRepository.findDeanByUsername(any())).thenReturn(dean);
		//act
		res = this.deanService.findDeanByUsername("cualquiera");
		//assert
		assertTrue(res.getFirstName().equals(dean.getFirstName()));
	}
	
	//NEGATIVE
	
	@Test
	@DisplayName("Finding dean by bad username")
	void testFindDeanByBadUsername() throws DataAccessException{
		//arrange
		Dean res;
		dean.setFirstName("depredadoor");
		when(this.deanRepository.findDeanByUsername(any())).thenReturn(null);
		//act
		res = this.deanService.findDeanByUsername("cualquiera");
		//assert
		assertTrue(res==null);
	}
	
}
