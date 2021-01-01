package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.College;
import org.springframework.samples.petclinic.model.Department;
import org.springframework.samples.petclinic.model.PersonalExperience;
import org.springframework.samples.petclinic.model.Score;
import org.springframework.samples.petclinic.model.Student;
import org.springframework.samples.petclinic.model.Subject;
import org.springframework.samples.petclinic.model.Teacher;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.ScoreService;
import org.springframework.samples.petclinic.service.StudentService;
import org.springframework.samples.petclinic.service.TeacherService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasProperty;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.assertj.core.util.Lists;

//@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.MOCK)
//@AutoConfigureMockMvc
@WebMvcTest(controllers = TeacherController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration = SecurityConfiguration.class)
public class TeacherControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ScoreService scoreService;
	
	@MockBean
	private TeacherService teacherService;
	
	@MockBean
	private StudentService studentService;
	
	Score score1;
	Teacher teacher1;
	Student student1;
	List<Score> scores;
	@BeforeEach
	void init() {
		teacher1 = new Teacher("nombre", new User(), Lists.list(new College()), 
				new PersonalExperience(), Lists.list(new Department()), Lists.list(new Subject()));
		teacher1.setId(80);
		student1 = new Student("estudiante", "elseñor@mastodonte.com", new User(), Lists.list(new Subject()), Lists.list(new Teacher()));
		student1.setId(75);
		score1 = new Score(5, "lo bueno de comentar es que te quedas agustisimo", new Student(), new Teacher());
		score1.setId(70);
		scores = new ArrayList<>(Lists.list(score1));
		
		when(this.studentService.findStudentByUsername(any())).thenReturn(student1);
		when(this.teacherService.findTeacherById(80)).thenReturn(teacher1);
		when(this.scoreService.findAll()).thenReturn(scores);
	}
	
	@Test
	@DisplayName("Test show teacher")
	@WithMockUser(value="spring")
	void SaveShowTeacherTest() {
		//arrange
		
		try {
			//act
			mockMvc.perform(get("/teachers/{teacherId}", 80))
			//assert
			.andExpect(status().isOk())
			.andExpect(model().attribute("teacher", hasProperty("firstName", is(teacher1.getFirstName()))));
		} catch (Exception e) {
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Test initCreationForm new Score")
	@WithMockUser(value="spring")
	void CreateScoreInitFormTest() {
		//arrange
		when(this.teacherService.findTeacherById(80)).thenReturn(teacher1);

		try {
			mockMvc.perform(get("/teachers/{teacherId}/scores/new", 80))
			.andExpect(status().isOk());
		} catch (Exception e) {
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Test ProcessCreationForm new Score")
	@WithMockUser(value = "spring")
	void CreateScoreProcessFormTest() {
		//arrange
		when(this.studentService.findStudentByUsername(any())).thenReturn(student1);
		when(this.teacherService.findTeacherById(80)).thenReturn(teacher1);
		when(this.scoreService.findAll()).thenReturn(scores);

			try {
				//act
				mockMvc.perform(post("/teachers/{teacherId}/scores/new", 80)
				.with(csrf())
					.param("valu", "8")
					.param("comment", "un comment cualquiera"))
				//assert
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("score"))
				.andExpect(model().attribute("score", hasProperty("valu", is(8))))
				.andExpect(model().attribute("score", hasProperty("comment", is("un comment cualquiera"))));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	

}
