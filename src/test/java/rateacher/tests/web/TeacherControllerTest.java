package rateacher.tests.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.ModelAndView;

import rateacher.configuration.SecurityConfiguration;
import rateacher.model.College;
import rateacher.model.Department;
import rateacher.model.PersonalExperience;
import rateacher.model.Score;
import rateacher.model.Student;
import rateacher.model.Subject;
import rateacher.model.Teacher;
import rateacher.model.User;
import rateacher.service.ScoreService;
import rateacher.service.StudentService;
import rateacher.service.SubjectService;
import rateacher.service.TeacherService;
import rateacher.web.TeacherController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.assertj.core.util.Lists;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
	
	@MockBean
	private SubjectService subjectService;
	
	Score score1;
	Teacher teacher1;
	Student student1;
	List<Score> scores;
	
	@BeforeEach
	void init() {
		teacher1 = new Teacher("nombre", new User(), Lists.list(new College()), new PersonalExperience(),
				Lists.list(new Department()), Lists.list(new Subject()));
		teacher1.setId(80);
		student1 = new Student("estudiante", "elseñor@mastodonte.com", new User(), Lists.list(new Subject()),
				Lists.list(new Teacher()));
		student1.setId(75);
		score1 = new Score(5, "lo bueno de comentar es que te quedas agustisimo", new Student(), new Teacher());
		score1.setId(70);
		scores = new ArrayList<>(Lists.list(score1));

	}
	
	@Test
	@DisplayName("Show teacher list")
	@WithMockUser(value="spring")
	void ShowTeacherListTest() {
		//arrange		
		when(this.studentService.findStudentByUsername(any())).thenReturn(student1);
		when(this.teacherService.findTeachers()).thenReturn(Lists.list(teacher1));
		try {
			//act
			mockMvc.perform(get("/teachers"))
			//assert
			.andExpect(status().isOk())
			.andExpect(view().name("teachers/teachersList"))
			.andExpect(model().attribute("student", hasProperty("name", is(student1.getName()))))
			.andExpect(model().attribute("teachers", hasItem(
                            hasProperty("name", is(teacher1.getName()))
            )));
		} catch (Exception e) {
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Show teacher")
	@WithMockUser(value="spring")
	void ShowTeacherTest() {
		//arrange		
		when(this.studentService.findStudentByUsername(any())).thenReturn(student1);
		when(this.teacherService.findTeacherById(80)).thenReturn(teacher1);
		when(this.scoreService.findAll()).thenReturn(scores);
		
		try {
			//act
			mockMvc.perform(get("/teachers/{teacherId}", 80))
			//assert
			.andExpect(status().isOk())
			.andExpect(view().name("teachers/teacherDetails"))
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
			.andExpect(status().isOk())
			.andExpect(model().attribute("teacher", hasProperty("firstName", is(teacher1.getFirstName()))));
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
	
	@Test
	@DisplayName("Show teacher found")
	@WithMockUser(value="spring")
	void teacherFoundTest() {
		//arrange		
		teacher1.setFirstName(teacher1.getName());
		when(this.teacherService.findTeacherByFirstName(any())).thenReturn(Lists.list(teacher1));
		try {
			//act
			mockMvc.perform(get("/teachersFound", teacher1)
					.param("firstName", teacher1.getFirstName()))
			//assert
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/teachers/" + teacher1.getId()));
		} catch (Exception e) {
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Test ProcessEditForm new Score")
	@WithMockUser(value = "spring")
	void EditScoreProcessFormTest() {
		//arrange
		Score score = new Score(4, "nuevo comentario", new Student(), new Teacher());
		when(this.teacherService.findTeacherById(80)).thenReturn(teacher1);
		when(this.scoreService.findScoreById(81)).thenReturn(score);

			try {
				//act
				mockMvc.perform(post("/teachers/{teacherId}/scores/{scoreId}/edit", 80, 81)
				.with(csrf())
					.param("valu", "9")
					.param("comment", "comentario editado"))
				//assert
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("score"))
				.andExpect(model().attribute("score", hasProperty("valu", is(9))))
				.andExpect(model().attribute("score", hasProperty("comment", is("comentario editado"))));
		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
//	@Test
//	@DisplayName("List my subjects")
//	@WithMockUser(value="spring")
//	void ListMySubjectsTest() {
//		//arrange
//		Subject subject = new Subject("mates", 2, new Department(), new ArrayList<>());
//		Teacher teacher2 = new Teacher("nombre", new User(), Lists.list(new College()), 
//				new PersonalExperience(), Lists.list(new Department()), Lists.list(subject));
//		List<Teacher> teachers = new ArrayList<>();
//		teachers.add(teacher2);
//		when(subjectService.findSubjectById(80)).thenReturn(subject);
//		when(teacherService.findTeachers()).thenReturn(teachers);
//		
//		try {
//			//act
//			mockMvc.perform(get("/teachers/{subjectId}/subjectsTeached", 80))
//			//assert
//			.andExpect(status().isOk())
//			.andExpect(view().name("teachers/subjectListTeached"))
//			.andExpect(model().attribute("teachers", hasItem(
//                            hasProperty("name", is(teacher2.getName()))
//            )));
//			
//		} catch (Exception e) {
//			System.err.println("Error testing controller: "+e.getMessage());
//			e.printStackTrace();
//		}
//	}
	
	@Test
	@DisplayName("Show Students who rated a teacher")
	@WithMockUser(value="spring")
	void showStudentsRatedATeacherTest() {
		//arrange
		Student student = new Student("manolo", "si@dime.es", new User(), new ArrayList<>(), new ArrayList<>());
		List<Student> students = new ArrayList<>();
		students.add(student);
		when(studentService.StudentsRatedATeacher(80)).thenReturn(students);
		
		try {
			//act
			mockMvc.perform(get("/teachers/{teacherId}/studentsRated", 80))
			//assert
			.andExpect(status().isOk())
			.andExpect(view().name("students/studentRatedATeacher"))
			.andExpect(model().attributeExists("students"))
			.andExpect(model().attribute("students", hasItem(
                    allOf(
                            hasProperty("name", is(student.getName())),
                            hasProperty("email", is(student.getEmail()))
                    )
            )));
			
		} catch (Exception e) {
			System.err.println("Error testing controller: "+e.getMessage());
			e.printStackTrace();
		}
	}

}

