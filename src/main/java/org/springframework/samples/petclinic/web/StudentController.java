package org.springframework.samples.petclinic.web;


import java.util.List;

import java.util.Collection;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Score;
import org.springframework.samples.petclinic.model.Student;
import org.springframework.samples.petclinic.model.Students;

import org.springframework.samples.petclinic.model.Subject;
import org.springframework.samples.petclinic.model.Teachers;

import org.springframework.samples.petclinic.model.Teacher;
import org.springframework.samples.petclinic.repository.TeacherRepository;
import org.springframework.samples.petclinic.service.ScoreService;

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

@Controller

public class StudentController {


	private static final String VIEWS_STUDENT_CREATE_OR_UPDATE_FORM = "students/createOrUpdateStudentForm";

	private final StudentService studentService;
	private final TeacherService teacherService;
	private final ScoreService scoreService;


	@Autowired
	public StudentController(StudentService studentService, TeacherService teacherService, ScoreService scoreService) {

		this.studentService = studentService;
		this.teacherService = teacherService;
		this.scoreService = scoreService;

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
	

	@GetMapping(value = "/subjects/mySubjects/{studentId}")
	public String listMySubjects(@PathVariable("studentId") int studentId, Map<String, Object> model ) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.put("principal", principal);
		List<Subject> subjects = studentService.findMySubjects(studentId);
		model.put("subjects", subjects);
		Student student = studentService.findStudentById(studentId);
		model.put("student", student);
		return "/students/mySubjects";

	}
	

	@GetMapping("students/{studentId}/showRatedTeachers")
	public ModelAndView showMyRatedTeachers(@PathVariable("studentId") int studentId) {
		ModelAndView mav = new ModelAndView("teachers/myRatedTeachersList");
		Student student = studentService.findStudentById(studentId);
		Collection<Teacher> teachers = teacherService.findTeacherByStudentId(student.getId());
		
		//"teachers" hace referencia a como se va a llamar en el .jsp
		mav.addObject("teachers",teachers);
		return mav;
	}
	
	



}
