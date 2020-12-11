package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Student;
import org.springframework.samples.petclinic.model.Students;
import org.springframework.samples.petclinic.model.Teachers;
import org.springframework.samples.petclinic.service.StudentService;
import org.springframework.samples.petclinic.service.TeacherService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class StudentController {


	private static final String VIEWS_STUDENT_CREATE_OR_UPDATE_FORM = "students/createOrUpdateStudentForm";

	private final StudentService studentService;
	private final TeacherService teacherService;


	@Autowired
	public StudentController(StudentService studentService, TeacherService teacherService) {

		this.studentService = studentService;
		this.teacherService = teacherService;

	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	/*
	 * @PostMapping("/myTeachers") public String listScoreTeacher(@Valid Teacher
	 * teacher, Student student, BindingResult result) { String res = "";
	 * 
	 * return res; }
	 */

	@GetMapping(value = { "/students" })
	public String showStudentsList(Map<String, Object> model) {

		Students students = new Students();
		students.getStudentList().addAll(this.studentService.findStudents());
		model.put("students", students);
		return "students/studentsList";

	}
	
	@GetMapping(value = { "/studentsWithScore" })
	public String showStudentsWithScore(Map<String, Object> model) {

		Students students = new Students();
		students.getStudentList().addAll(this.studentService.studentWithScore());
		model.put("students", students);
		return "students/studentsWithScore";

	}
	
	@GetMapping(value = "/students/new")
	public String initCreationForm(Map<String, Object> model) {
		Student student = new Student();
		model.put("student", student);
		return VIEWS_STUDENT_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/students/new")
	public String processCreationForm(@Valid Student student, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_STUDENT_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating student, user and authorities
			this.studentService.saveStudent(student);
			
			return "redirect:/students/" + student.getId();
		}
	}
	
	@GetMapping(value = { "/myTeachers" })
	public String showTeacherList(Map<String, Object> model) {
		String principal = SecurityContextHolder.getContext().getAuthentication().getName();
		Student student = this.studentService.findStudentByUsername(principal);
		Teachers teachers = new Teachers();
		teachers.getTeachersList().addAll(this.studentService.myTeachers());
		model.put("teachers", teachers);
		return "teachers/teachersList";

	}
	
//	@GetMapping(value = "/studentsForm")
//	public String studentsForm(Map<String, Object> model) {
//		model.put("Students", new Student());
//		return "students/studentsForm";
//	}
	
//	@GetMapping(value = "/students/new")
//	public String initCreationForm(Map<String, Object> model) {
//		Student student = new Student();
//		model.put("student", student);
//		return "student/createOrUpdateStudentForm";
//	}
	
//	@GetMapping(value = { "/myTeachers/{subjectid}" })
//	public String showMyScorableTeachersList(Map<String, Object> model, @PathVariable("subjectid") int i) {
//
//		Teachers teachers = new Teachers();
//		teachers.getTeachersList().addAll(this.teacherService.findTeacherBySubject(i));
//		model.put("teachers", teachers);
//		return "teachers/scorableTeachers";
//		
//	} 
	
//	@GetMapping("/myTeachers/{teacherId}")
//	public ModelAndView showStudent(@PathVariable("teacherId") int teacherId) {
//		ModelAndView mav = new ModelAndView("teachers/scorableTeachers");
//		mav.addObject(this.teacherService.findTeacherBySubject(teacherId));
//		return mav;
//	}
	
//
//	@GetMapping(value = { "/students.xml" })
//	public @ResponseBody Students showResourcesTeacherList() {
//
//		Students students = new Students();
//		students.getStudentList().addAll(this.studentService.studentWithScore());
//		return students;
//	}

}
