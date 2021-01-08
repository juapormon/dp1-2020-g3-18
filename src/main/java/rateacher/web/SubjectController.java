package rateacher.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import rateacher.model.Subject;
import rateacher.model.Subjects;
import rateacher.model.Teacher;
import rateacher.repository.DeanRepository;
import rateacher.repository.SubjectRepository;
import rateacher.service.SubjectService;

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
