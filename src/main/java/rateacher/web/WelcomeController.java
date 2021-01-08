package rateacher.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;

import rateacher.model.Student;
import rateacher.model.User;
import rateacher.repository.UserRepository;
import rateacher.service.StudentService;

import java.util.List;

@Controller
public class WelcomeController {

	public WelcomeController(StudentService studentService) {
		this.studentService = studentService;
	}

	private StudentService studentService;

	@InitBinder

	public void setAllowedFields(WebDataBinder dataBinder) {

		dataBinder.setDisallowedFields("id");
	}

	@GetMapping({ "/", "/welcome" })
	public String welcome(Map<String, Object> model) {

		String principal = SecurityContextHolder.getContext().getAuthentication().getName();

		Student student = this.studentService.findStudentByUsername(principal);

		model.put("student", student);

		return "welcome";
	}

}
