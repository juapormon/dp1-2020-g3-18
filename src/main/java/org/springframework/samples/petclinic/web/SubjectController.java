package org.springframework.samples.petclinic.web;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Subjects;
import org.springframework.samples.petclinic.model.Student;
import org.springframework.samples.petclinic.model.Subject;
import org.springframework.samples.petclinic.model.Teacher;
import org.springframework.samples.petclinic.repository.DeanRepository;
import org.springframework.samples.petclinic.repository.SubjectRepository;
import org.springframework.samples.petclinic.service.SubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/subjects")
public class SubjectController {
	
	private SubjectService subjectService;
	
	@Autowired
	public SubjectController(SubjectService subjectService) {
		this.subjectService = subjectService;
	}

	@GetMapping(value = {"/new"})
	public String newSubject(ModelMap modelMap) {
		String subject = "subjects/newSubject";
		modelMap.addAttribute("subjects", new Subject());
		return subject;
	}
	
	@PostMapping(value = {"/save"})
	public String processCreationForm(@Valid Subject subject, BindingResult result, ModelMap modelMap) {
		String view = "subjects/subjectsList";
		if (result.hasErrors()) {
			modelMap.addAttribute("subject", subject);
			return "subjects/newSubject";
		}
		else {
			subjectService.save(subject);
			modelMap.addAttribute("message", "Subject successfully saved!");
			view = showSubjectsList(modelMap);
		}
		return view;
	}
	
	@GetMapping(path="/delete/{subjectId}")
	public String deleteSubject(@PathVariable("subjectId") int subjectId, ModelMap modelMap){
		String view = "subjects/subjectsList";
		Optional<Subject> subject = subjectService.findSubjectById(subjectId);
		if(subject.isPresent()) {
			subjectService.delete(subject.get());
			modelMap.addAttribute("message", "Subject successfully deleted!");
			view = showSubjectsList(modelMap);
		} else {
			modelMap.addAttribute("message", "Subject not found!");
			view = showSubjectsList(modelMap);
		}
		return view;
	}
	
	
	@GetMapping(value = {""})
	public String showSubjectsList(Map<String, Object> model) {
		Subjects subjects = new Subjects();
		subjects.getSubjectList().addAll(this.subjectService.findSubjects());
		model.put("subjects", subjects);
		return "subjects/subjectsList";
	}	
}
