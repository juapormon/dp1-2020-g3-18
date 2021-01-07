package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Subjects;
import org.springframework.samples.petclinic.model.Subject;
import org.springframework.samples.petclinic.model.Teacher;
import org.springframework.samples.petclinic.repository.DeanRepository;
import org.springframework.samples.petclinic.repository.SubjectRepository;
import org.springframework.samples.petclinic.service.SubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/subjects")
public class SubjectController {
	private static final String VIEW_SUBJECT_CREATE_FORM ="subjects/newSubject";
	@Autowired
	private SubjectService subjectService;
	
	

	@GetMapping(value = {"/subjects/new"})
	public String newSubject(ModelMap model) {

		Subject subject = new Subject();
		model.put("subjects", subject);
		return VIEW_SUBJECT_CREATE_FORM;

	}
	
	@GetMapping(value = { "" })
	public String showSubjectsList(Map<String, Object> model) {

		Subjects subjects = new Subjects();
		subjects.getSubjectList().addAll(this.subjectService.findSubjects());
		model.put("subjects", subjects);
		return "subjects/subjectsList";

	}
	
	
	
}
