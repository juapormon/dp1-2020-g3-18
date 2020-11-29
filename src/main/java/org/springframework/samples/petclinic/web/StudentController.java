package org.springframework.samples.petclinic.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Students;
import org.springframework.samples.petclinic.model.Subject;
import org.springframework.samples.petclinic.model.Teachers;
import org.springframework.samples.petclinic.repository.StudentRepository;
import org.springframework.samples.petclinic.repository.TeacherRepository;
import org.springframework.samples.petclinic.service.StudentService;
import org.springframework.samples.petclinic.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.bytebuddy.asm.Advice.This;

@Controller
//@RequestMapping(name = "students")
public class StudentController {



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
	
//	@GetMapping(value = { "/myTeachers/{subjectid}" })
//	public String showMyScorableTeachersList(Map<String, Object> model, @PathVariable("subjectid") int i) {
//
//		Teachers teachers = new Teachers();
//		teachers.getTeachersList().addAll(this.teacherService.findTeacherBySubject(i));
//		model.put("teachers", teachers);
//		return "teachers/scorableTeachers";
//		
//	} 
	
	@GetMapping("/myTeachers/{teacherId}")
	public ModelAndView showMyTeachers(@PathVariable("teacherId") Integer teacherId) {
		ModelAndView mav = new ModelAndView("teachers/scorableTeachers");
		mav.addObject(this.teacherService.findTeacherBySubject(teacherId));
		return mav;
	}
	
//
//	@GetMapping(value = { "/students.xml" })
//	public @ResponseBody Students showResourcesTeacherList() {
//
//		Students students = new Students();
//		students.getStudentList().addAll(this.studentService.studentWithScore());
//		return students;
//	}

}
