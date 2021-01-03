package org.springframework.samples.petclinic.web;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.College;
import org.springframework.samples.petclinic.model.Dean;
import org.springframework.samples.petclinic.model.Score;
import org.springframework.samples.petclinic.model.Teacher;
import org.springframework.samples.petclinic.repository.CollegeRepository;
import org.springframework.samples.petclinic.repository.DeanRepository;
import org.springframework.samples.petclinic.repository.TeacherRepository;
import org.springframework.samples.petclinic.service.CollegeService;
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
import org.springframework.web.bind.annotation.PutMapping;

import javassist.expr.NewArray;

@Controller
//@RequestMapping("/deans/{deanId}")
public class DeanController {
	
	private final DeanService deanService;
	private final TeacherRepository teachers;
	private final CollegeRepository collRepo;
	private final TeacherService teacherService;
	private final CollegeService collegeService;
	
	private static final String VIEW_TEACHER_CREATE_FORM ="teachers/newTeacherCreationForm";
	
	
	@Autowired
	public DeanController( TeacherRepository teachers, CollegeRepository collRepo, DeanService deanService, TeacherService teacherService, CollegeService collegeService) {
		this.teachers= teachers;
		this.deanService= deanService;
		this.teacherService= teacherService;
		this.collegeService= collegeService;
		this.collRepo = collRepo;
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
		Collection<Teacher> teachers = this.teacherService.findTeachers();
		model.put("collegeId", collegeId);
		model.put("teachers", teachers);
		return "deans/teachersByCollegeList";
	}
	
	@GetMapping(value = { "/deans/colleges/{collegeId}/teachers/{teacherId}/add" })
	public String addTeacherToCollege(@PathVariable int collegeId, @PathVariable int teacherId, Map<String, Object> model) {
		model.put("collegeId", collegeId);
		Teacher teacherAdded = this.teacherService.findTeacherById(teacherId);
		model.put("teacher", teacherAdded);
		return "deans/AreYouSureView";
	}
	
	
	@PostMapping("/deans/colleges/{collegeId}/teachers/{teacherId}/add")
	public String addTeacherToCollegeProcess(@PathVariable int collegeId, @PathVariable int teacherId, Teacher teacherd, Map<String, Object> model, BindingResult result) {
		College college = collegeService.findCollegeById(collegeId);
		List<Integer> teacherIds = new ArrayList<Integer>();
		for(Teacher teacher: college.getTeachers()) {
			teacherIds.add(teacher.getId());
		}
		if(teacherIds.contains(teacherId)) { //revisar
			model.put("collegeId", collegeId);
			Teacher teacherAdded = this.teacherService.findTeacherById(teacherId);
			model.put("teacher", teacherAdded);
			Collection<Teacher> teachers = this.teacherService.findTeachers();
			model.put("teachers", teachers);
			model.put("nono", "Error: No puedes añadir a ese profesor porque ya esta en la lista");
			result.rejectValue("name", "This teacher is already in the college");
			return "deans/teachersByCollegeList";
		}else {
		Teacher teacherAdded = this.teacherService.findTeacherById(teacherId);
		teacherAdded.setName(teacherAdded.getFirstName());
		college.getTeachers().add(teacherAdded);	
		this.collRepo.save(college);
		Collection<College> colleges = this.deanService.findAllColleges();
		model.put("colleges", colleges);
		}
		return "deans/collegesList";
		
	}

}

