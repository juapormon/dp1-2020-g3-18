
package org.springframework.samples.petclinic.web;


import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Teacher;
import org.springframework.samples.petclinic.model.Teachers;
import org.springframework.samples.petclinic.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public ModelAndView showTeacher(@PathVariable("teacherId") int teacherId) {
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
	
}



