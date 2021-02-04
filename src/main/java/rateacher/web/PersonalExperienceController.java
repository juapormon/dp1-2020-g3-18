package rateacher.web;

import java.util.Collection;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;

import rateacher.model.PersonalExperience;
import rateacher.model.ResearchExperience;
import rateacher.model.Score;
import rateacher.model.Student;
import rateacher.model.Subject;
import rateacher.model.Teacher;
import rateacher.model.TeachingExperience;
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
	
	@GetMapping(value = { "/teachers/{teacherId}/newPersonalExperience" })
	public String initCreationPersonalExperienceForm(@PathVariable int teacherId, ModelMap model) {																		// vista.
		PersonalExperience personalExperience = new PersonalExperience();
		Teacher teacher = this.teacherService.findTeacherById(teacherId);
		model.put("personalExperience", personalExperience);
		model.put("teacher", teacher);
		return "personalExperience/createPersonalExperienceForm";
	}
	
	@PostMapping(value = "/teachers/{teacherId}/newPersonalExperience")
	public String processCreationPersonalExperienceForm(@PathVariable int teacherId, @Valid PersonalExperience personalExperience, BindingResult result,
			ModelMap model) {
			if (result.hasErrors()) {
				Teacher teacher = teacherService.findTeacherById(teacherId);
				model.put("teacher", teacher);
				return "personalExperience/createPersonalExperienceForm";
			} else {
				personalExperienceService.save(personalExperience);
				Teacher teacher = teacherService.findTeacherById(teacherId);
				teacher.setPersonalExperience(personalExperience);
				teacherService.save(teacher);
				model.put("personalExperience", personalExperience);
				model.put("teacher", teacher);
				Collection<Teacher> teachers = this.teacherService.findTeachers();
				model.put("teachers", teachers);
				return "teachers/teachersList";
			}		
	}
	
	@GetMapping(value = "/personalExperience/{personalExperienceId}")
	public String showTeacher(@PathVariable("personalExperienceId") int personalExperieneId, ModelMap model) {
		PersonalExperience p = personalExperienceService.findById(personalExperieneId);
		model.put("personalExperience", p);
		return "personalExperience/personalExperience";
	}
	
	@GetMapping(value = { "/personalExperience/{personalExperienceId}/newResearchExperience" })
	public String initCreationResearchExperienceForm(@PathVariable int personalExperienceId, ModelMap model) {																		// vista.
		PersonalExperience personalExperience = personalExperienceService.findById(personalExperienceId);
		ResearchExperience re = new ResearchExperience();
		model.put("personalExperience", personalExperience);
		model.put("researchExperience", re);
		return "personalExperience/researchExperience";
	}
	
	
	// POST
	
	
	@GetMapping(value = { "/personalExperience/{personalExperienceId}/newTeachingExperience" })
	public String initCreationTeachingExperienceForm(@PathVariable int personalExperienceId, ModelMap model) {																		// vista.
		PersonalExperience personalExperience = personalExperienceService.findById(personalExperienceId);
		TeachingExperience te = new TeachingExperience();
		model.put("personalExperience", personalExperience);
		model.put("teachingExperience", te);
		return "personalExperience/teachingExperience";
	}
	
	
	
	
}
