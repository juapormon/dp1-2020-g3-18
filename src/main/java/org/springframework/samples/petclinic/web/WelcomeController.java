package org.springframework.samples.petclinic.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.repository.UserRepository;
import org.springframework.samples.petclinic.service.StudentService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.samples.petclinic.model.Student;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.List;

@Controller
public class WelcomeController {
	
	private StudentService studentService;
	
	
	@Autowired
	public WelcomeController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	 @GetMapping({"/","/welcome"})
	 public String welcome( Map<String, Object> model) {	  
//		if(SecurityContextHolder.getContext().getAuthentication() != null) {
//			String principal = SecurityContextHolder.getContext().getAuthentication().getName();
//			Student student = this.studentService.findStudentByUsername(principal);
//			Integer studentId = student.getId();
//			model.put("studentId", studentId);
//		 }
	    return "welcome";
	 }
}
