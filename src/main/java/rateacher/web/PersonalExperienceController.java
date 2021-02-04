package rateacher.web;

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

import rateacher.model.PersonalExperience;
import rateacher.model.Score;
import rateacher.model.Student;
import rateacher.model.Teacher;
import rateacher.service.PersonalExperienceService;
import rateacher.service.ScoreService;
import rateacher.service.StudentService;
import rateacher.service.TeacherService;

@Controller
public class PersonalExperienceController {

	
	private final PersonalExperienceService personalExperienceService;
	private final TeacherService teacherService;
	
	@Autowired
	public PersonalExperienceController(PersonalExperienceService personalExperienceService, TeacherService teacherService) {
		this.personalExperienceService = personalExperienceService;
		this.teacherService = teacherService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = { "teachers/{teacherId}/newPersonalExperience" })
	public String initCreationPersonalExperienceForm(@PathVariable int teacherId, ModelMap model) { // para crear el modelo que va a la																			// vista.
		PersonalExperience personalExperience = new PersonalExperience();
		Teacher teacher = this.teacherService.findTeacherById(teacherId);
		teacher.setPersonalExperience(personalExperience);
		model.put("personalExperience", personalExperience);
		model.put("teacher", teacher);
		return "personalExperience/createPersonalExperienceForm";
	}
	
	@PostMapping(value = "teachers/{teacherId}/newPersonalExperience")
	public String processCreationPersonalExperienceForm(@PathVariable int teacherId, @Valid PersonalExperience personalExperience, BindingResult result,
			ModelMap model) {
			model.put("personalExperience", personalExperience);
			Teacher teacher = teacherService.findTeacherById(teacherId);
			model.put("teacher", teacher);
			return "personalExperience/createPersonalExperienceForm";
		
	}
}
