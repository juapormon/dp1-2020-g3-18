package org.springframework.samples.petclinic.web;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Subjects;
import org.springframework.samples.petclinic.model.Student;
import org.springframework.samples.petclinic.model.Subject;
import org.springframework.samples.petclinic.model.Teacher;
import org.springframework.samples.petclinic.repository.DeanRepository;
import org.springframework.samples.petclinic.repository.SubjectRepository;
import org.springframework.samples.petclinic.service.StudentService;
import org.springframework.samples.petclinic.service.SubjectService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class SubjectController {
	private static final String VIEW_SUBJECT_CREATE_FORM ="subjects/newSubject";
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private StudentService studentService;
	
	
	

	@GetMapping(value = {"/subjects/new"})
	public String newSubject(ModelMap model) {
		
		Subject subject = new Subject();
		model.put("subjects", subject);
		return VIEW_SUBJECT_CREATE_FORM;

	}
	
	@PostMapping(value = {"/subjects/save"})
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
	
	@GetMapping(path="/subjects/delete/{subjectId}")
	public String deleteSubject(@PathVariable("subjectId") int subjectId, ModelMap modelMap){
		String view = "subjects/subjectsList";
		Optional<Subject> subject = Optional.ofNullable(subjectService.findSubjectById(subjectId));
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
	
	@GetMapping(value = { "/subjects" })
	public String showSubjectsList(Map<String, Object> model) {
		Subjects subjects = new Subjects();
		subjects.getSubjectList().addAll(this.subjectService.findSubjects());
		model.put("subjects", subjects);
		String principal = SecurityContextHolder.getContext().getAuthentication().getName();
        Student student = this.studentService.findStudentByUsername(principal);
        Boolean condition = (student != null);
        model.put("condition", condition);
        model.put("student", student);
		return "subjects/subjectsList";

	}
	
//	@GetMapping(value = "/subjects/mySubjects/{studentId}")
//	public ModelAndView listMySubjects(@PathVariable("studentId") int studentId) {
//		ModelAndView mav = new ModelAndView("students/mySubjects");
//		Student student = studentService.findStudentById(studentId);
//		List<Subject> subjects = studentService.findMySubjects(studentId);
//		mav.addObject("subjects", subjects);
//		mav.addObject("studentId", studentId);
//		return mav;
//	}
	
	
	
}
