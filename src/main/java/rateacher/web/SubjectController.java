package rateacher.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import rateacher.model.Student;
import rateacher.model.Subject;
import rateacher.model.Subjects;
import rateacher.model.Teacher;
import rateacher.service.StudentService;
import rateacher.service.SubjectService;
import rateacher.service.TeacherService;



@Controller
public class SubjectController {
	private static final String VIEW_SUBJECT_CREATE_FORM ="subjects/newSubject";
	
	private final SubjectService subjectService;
	private final TeacherService teacherService;
	private final StudentService studentService;
	
	@Autowired
	public SubjectController(SubjectService subjectService, TeacherService teacherService, StudentService studentService) {
		this.subjectService = subjectService;
		this.teacherService = teacherService;
		this.studentService = studentService;
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
	
	
	@GetMapping(value = { "/subjects/{subjectId}/teachers" })
	public String showTeacherByCollegesList(@PathVariable int subjectId, Map<String, Object> model) {
		Collection<Teacher> teachers = this.teacherService.findTeachers();
		model.put("subjectId", subjectId);
		model.put("teachers", teachers);
		return "subjects/teachersList";
	}
	
	@GetMapping(value = { "/subjects/{subjectId}/teachers/{teacherId}/add" })
	public String addTeacherToCollege(@PathVariable int subjectId, @PathVariable int teacherId, Map<String, Object> model) {
		model.put("subjectId", subjectId);
		Teacher teacherAdded = this.teacherService.findTeacherById(teacherId);
		model.put("teacher", teacherAdded);
		return "subjects/AreYouSureView";
	}
	
	@PostMapping("/subjects/{subjectId}/teachers/{teacherId}/add")
	public String addTeacherToCollegeProcess(@PathVariable int subjectId, @PathVariable int teacherId, Teacher teacherd, Map<String, Object> model, BindingResult result) {
		Subject subject = subjectService.findSubjectById(subjectId);
		List<Integer> teacherIds = new ArrayList<Integer>();
		for(Teacher teacher: subject.getTeachers()) {
			teacherIds.add(teacher.getId());
		}
//		if(teacherIds.contains(teacherId)) { //regla de negocio a implementar
//			model.put("subjectId", subjectId);
//			Teacher teacherAdded = this.teacherService.findTeacherById(teacherId);
//			model.put("teacher", teacherAdded);
//			Collection<Teacher> teachers = this.teacherService.findTeachers();
//			model.put("teachers", teachers);
//			model.put("nono", "Error: No puedes añadir a ese profesor porque ya esta en la lista");
//			result.rejectValue("name", "This teacher is already in the subject");
//			return "subjects/teachersList";
//		}else {
		Teacher teacherAdded = this.teacherService.findTeacherById(teacherId);
		teacherAdded.setName(teacherAdded.getFirstName());
		teacherAdded.getSubjects().add(subject);	
		this.teacherService.save(teacherAdded);
		Collection<Subject> subjects = this.subjectService.findAll();
		model.put("subjects", subjects);
//		}
		return "subjects/subjectsList";
		
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
	
	
	
}

