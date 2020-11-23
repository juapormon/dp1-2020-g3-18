
package org.springframework.samples.petclinic.web;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Teachers;
import org.springframework.samples.petclinic.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TeacherController {

	
	private final TeacherService teacherService;

	@Autowired
	public TeacherController(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = { "/teachers" })
	public String showTeacherList(Map<String, Object> model) {
		
//		Teachers teachers = new Teachers();
//		teachers.getTeachersList().addAll( this.teacherService.findTeachers());
//		model.put("teachers", teachers);
		Teachers teachers = new Teachers();
		teachers.getTeachersList().addAll(this.teacherService.findTeachers());
		model.put("teachers", teachers);
		return "teachers/teachersList";
		
	}
	
	@GetMapping(value = { "/teachers.xml"})
	public @ResponseBody Teachers showResourcesTeacherList() {

		Teachers teachers = new Teachers();
		teachers.getTeachersList().addAll( this.teacherService.findTeachers());
		return teachers;
	}
	
	
}

