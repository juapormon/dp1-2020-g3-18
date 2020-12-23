package org.springframework.samples.petclinic.web;


import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.College;
import org.springframework.samples.petclinic.model.Dean;
import org.springframework.samples.petclinic.model.Score;
import org.springframework.samples.petclinic.model.Teacher;
import org.springframework.samples.petclinic.repository.DeanRepository;
import org.springframework.samples.petclinic.repository.TeacherRepository;
import org.springframework.samples.petclinic.service.DeanService;
import org.springframework.samples.petclinic.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
//@RequestMapping("/deans/{deanId}")
public class DeanController {
	
	private final DeanService deanService;
	private final TeacherRepository teachers;
	private final TeacherService teacherService;
	
	private static final String VIEW_TEACHER_CREATE_FORM ="teachers/newTeacherCreationForm";
	
	
	@Autowired
	public DeanController( TeacherRepository teachers, DeanService deanService, TeacherService teacherService) {
		this.teachers= teachers;
		this.deanService= deanService;
		this.teacherService= teacherService;
	}
	
	@InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }


	@GetMapping(value = {"/teachers/new"})
	public String newTeacherCreationForm(ModelMap model) {

		Teacher teacher = new Teacher();
		model.put("teachers", teacher);
		return VIEW_TEACHER_CREATE_FORM;

	}
	
	@PostMapping("/teachers/new")
	public String processCreationForm(@Valid Teacher teacher, BindingResult result) {
		if (result.hasErrors()) {
			return VIEW_TEACHER_CREATE_FORM;
		}
		else {
			this.teachers.save(teacher);
			return "redirect:/teachers";
		}
	}
	
	@GetMapping(value = { "/deans" })
	public String showDeansList(Map<String, Object> model) {
		Collection<Dean> deans = this.deanService.findAll();
		model.put("deans", deans);
		return "deans/deansList";
	}
	
	@GetMapping(value = { "/deans/colleges" })
	public String showCollegesList(Map<String, Object> model) {
		Collection<College> college = this.deanService.findAllColleges();
		model.put("colleges", college);
		return "deans/collegesList";
	}
	
	@GetMapping(value = { "/deans/colleges/{collegeId}/teachers" })
	public String showTeacherByCollegesList(@PathVariable int collegeId, Map<String, Object> model) {
		Collection<Teacher> teachers = this.teacherService.findTeacherByCollege(collegeId);
		model.put("teachers", teachers);
		return "deans/teachersByCollegeList";
	}
	
	
//	@PostMapping("/deans/teachers/{teacherId}/colleges/{collegeId}/add")
//	public String 


}

