
package org.springframework.samples.petclinic.web;


import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Score;
import org.springframework.samples.petclinic.model.Scores;
import org.springframework.samples.petclinic.model.Student;
import org.springframework.samples.petclinic.model.Teacher;
import org.springframework.samples.petclinic.model.Teachers;
import org.springframework.samples.petclinic.service.StudentService;
import org.springframework.samples.petclinic.service.TeacherService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TeacherController {

	
	private final TeacherService teacherService;
	private final StudentService studentService;

	@Autowired
	public TeacherController(TeacherService teacherService, StudentService studentService) {
		this.teacherService = teacherService;
		this.studentService = studentService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = { "/teachers" })
	public String showTeacherList(Map<String, Object> model) {
		
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
	
	
	@GetMapping(value = "/teachers/{teacherId}") 
	public ModelAndView showTeacher(@PathVariable("teacherId") int teacherId, Map<String, Object> model) {
		ModelAndView mav = new ModelAndView("teachers/teacherDetails");
		mav.addObject(this.teacherService.findTeacherById(teacherId));
		return mav;
	}

	@GetMapping(value = { "/teachersWithScore" })
	public String showTeacherWithScore(Map<String, Object> model) {  

		Teachers teachers = new Teachers();
		teachers.getTeachersList().addAll(this.teacherService.showTeacherWithScore());
		model.put("teachers", teachers);
		return "teachers/teachersWithScore";
		
	}
	
	
	@GetMapping(value = "/findTeachers")
	public String initFindForm(Map<String, Object> model) {
		model.put("teachers", new Teacher());
		return "teachers/findTeachers";
	}
	
	@GetMapping(value = "/teachersFound")
	public String processFindForm(Teacher teacher, BindingResult result, Map<String, Object> model) {

		// allow parameterless GET request for /owners to return all records
		if (teacher.getLastName() == null) {
			teacher.setLastName(""); // empty string signifies broadest possible search
		}

		// find owners by last name
		Collection<Teacher> results = this.teacherService.findOwnerByLastName(teacher.getLastName());
		if (results.isEmpty()) {
			// no owners found
			result.rejectValue("lastName", "notFound", "not found");
			return "teachers/findTeachers";
		}
		else if (results.size() == 1) {
			// 1 owner found
			teacher = results.iterator().next();
			return "redirect:/teachers/" + teacher.getId();
		}
		else {
			// multiple owners found
			model.put("selections", results);
			return "teachers/teachersList";
		}
	}
	
	@GetMapping(value = { "/teachers/{teacherId}/scores" }) 
	public String showTeacherScoreList(@PathVariable("teacherId") int teacherId, Map<String, Object> model) {
		Scores scores = new Scores();
		scores.getScoreList().addAll(this.teacherService.findScoresByTeacherId(teacherId));
		model.put("scores", scores);
		return "scores/scoresList"; 
	} 
	
//	@GetMapping(value = { "/teachers/{teacherId}/scores/comments" })  
//	public String showTeacherCommentList(Teacher teacher, Map<String, Object> model) {
//		List<String> comments = new ArrayList<>();
//		Scores scores = new Scores();
//		comments.addAll(this.scoreService.findTeacherCommentById(teacher.getId()));
//		model.put("comments", comments);
//		model.put("scores", scores);
//		return "scores/teacherCommentList";
//	}
	
	@GetMapping(value = { "/teachers/{teacherId}//scores/new"})
	public String initCreationForm(@PathVariable int teacherId, ModelMap model) { //para crear el modelo que va a la vista.
		Score score = new Score();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getCredentials(); 
		Object principal1 = SecurityContextHolder.getContext().getAuthentication().getDetails(); 
		Object principal2 = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		Integer userId = Integer.parseInt(principal.toString());
		Student student = this.studentService.findStudentById(userId);
		score.setStudent(student);
		Teacher teacher = teacherService.findTeacherById(teacherId);
		score.setTeacher(teacher);
		model.put("teacher", teacher);
		model.put("score", score);
		return "scores/createForm";		
	}
	
//	@PostMapping(value = "/scores/new")
//	public String processCreationForm(Teacher teacher, @Valid Score score, BindingResult result, ModelMap model) {		
//		if (result.hasErrors()) {
//			model.put("score", score);
//			return "scores/createForm";
//		}
//		else {
//            teacher.addScore(score);
//            this.scoreService.saveScore(score);
//
//            return "redirect:/teachers/{teacherId}";
//		}
//	}	
	
}



