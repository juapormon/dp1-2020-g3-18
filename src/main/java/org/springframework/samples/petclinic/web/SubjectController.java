package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Subjects;
import org.springframework.samples.petclinic.model.College;
import org.springframework.samples.petclinic.model.Subject;
import org.springframework.samples.petclinic.model.Teacher;
import org.springframework.samples.petclinic.repository.DeanRepository;
import org.springframework.samples.petclinic.repository.SubjectRepository;
import org.springframework.samples.petclinic.service.SubjectService;
import org.springframework.samples.petclinic.service.TeacherService;
import org.springframework.samples.petclinic.util.ScoreValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/subjects")
public class SubjectController {
	private static final String VIEW_SUBJECT_CREATE_FORM ="subjects/newSubject";
	
	private final SubjectService subjectService;
	private final TeacherService teacherService;
	
	@Autowired
	public SubjectController(SubjectService subjectService, TeacherService teacherService) {
		this.subjectService = subjectService;
		this.teacherService = teacherService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	
	@GetMapping(value = {"/subjects/new"})
	public String newSubject(ModelMap model) {

		Subject subject = new Subject();
		model.put("subjects", subject);
		return VIEW_SUBJECT_CREATE_FORM;

	}
	
	@GetMapping(value = { "" })
	public String showSubjectsList(Map<String, Object> model) {
		List<Subject> subjects = new ArrayList<>();
		subjects.addAll(this.subjectService.findAll());
		model.put("subjects", subjects);
		return "subjects/subjectsList";

	}
	
	@GetMapping(value = { "/{subjectId}/teachers" })
	public String showTeacherByCollegesList(@PathVariable int subjectId, Map<String, Object> model) {
		Collection<Teacher> teachers = this.teacherService.findTeachers();
		model.put("subjectId", subjectId);
		model.put("teachers", teachers);
		return "subjects/teachersList";
	}
	
	@GetMapping(value = { "{subjectId}/teachers/{teacherId}/add" })
	public String addTeacherToCollege(@PathVariable int subjectId, @PathVariable int teacherId, Map<String, Object> model) {
		model.put("subjectId", subjectId);
		Teacher teacherAdded = this.teacherService.findTeacherById(teacherId);
		model.put("teacher", teacherAdded);
		return "subjects/AreYouSureView";
	}
	
	@PostMapping("{subjectId}/teachers/{teacherId}/add")
	public String addTeacherToCollegeProcess(@PathVariable int subjectId, @PathVariable int teacherId, Teacher teacherd, Map<String, Object> model, BindingResult result) {
		Subject subject = subjectService.findSubjectById(subjectId);
		List<Integer> teacherIds = new ArrayList<Integer>();
		for(Teacher teacher: subject.getTeachers()) {
			teacherIds.add(teacher.getId());
		}
		if(teacherIds.contains(teacherId)) { //revisar
			model.put("subjectId", subjectId);
			Teacher teacherAdded = this.teacherService.findTeacherById(teacherId);
			model.put("teacher", teacherAdded);
			Collection<Teacher> teachers = this.teacherService.findTeachers();
			model.put("teachers", teachers);
			model.put("nono", "Error: No puedes añadir a ese profesor porque ya esta en la lista");
			result.rejectValue("name", "This teacher is already in the subject");
			return "subjects/teachersList";
		}else {
		Teacher teacherAdded = this.teacherService.findTeacherById(teacherId);
		teacherAdded.setName(teacherAdded.getFirstName());
		teacherAdded.getSubjects().add(subject);	
		this.teacherService.save(teacherAdded);
		Collection<Subject> subjects = this.subjectService.findAll();
		model.put("subjects", subjects);
		}
		return "subjects/subjectsList";
		
	}
	
	
	
	
	
}
