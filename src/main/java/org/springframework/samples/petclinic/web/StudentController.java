package org.springframework.samples.petclinic.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Student;
import org.springframework.samples.petclinic.model.Teacher;
import org.springframework.samples.petclinic.repository.StudentRepository;
import org.springframework.samples.petclinic.repository.TeacherRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(name ="students")
public class StudentController {

	private final StudentRepository students;
	private final TeacherRepository teachers;
	
	@Autowired
	public StudentController(StudentRepository students, TeacherRepository teachers) {
		this.students = students;
		this.teachers = teachers;
	}
	
	@InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

	@PostMapping("/myTeachers")
	public String listScoreTeacher(@Valid Teacher teacher, Student student, BindingResult result) {
		String res ="";
		
	
		
		
		return res;
	}
	
	
	
	
	
}
