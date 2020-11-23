package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Score;
import org.springframework.samples.petclinic.model.Scores;
import org.springframework.samples.petclinic.model.Teacher;
import org.springframework.samples.petclinic.service.ScoreService;
import org.springframework.samples.petclinic.service.TeacherService;
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
@RequestMapping("/teachers/{teacherId}")
public class ScoreController2 {

	private final ScoreService scoreService;
	private final TeacherService teacherService;

	@Autowired
	public ScoreController2(ScoreService clinicService, TeacherService teacherService) {
		this.scoreService = clinicService;
		this.teacherService = teacherService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@ModelAttribute("teacher")
	public Teacher findTeacher(@PathVariable("teacherId") int teacherId) {
		return this.teacherService.findTeacherById(teacherId);
	}
	
	@GetMapping(value = { "/scores/new"})
	public String initCreationForm(Teacher teacher, ModelMap model) { //para crear el modelo que va a la vista.
		Score score = new Score();
		teacher.addScore(score);
		model.put("score", score);
		return "scores/createForm";		
	}
	
	@PostMapping(value = "/scores/new")
	public String processCreationForm(Teacher teacher, @Valid Score score, BindingResult result, ModelMap model) {		
		if (result.hasErrors()) {
			model.put("score", score);
			return "scores/createForm";
		}
		else {
            teacher.addScore(score);
            this.scoreService.saveScore(score);

            return "redirect:/teachers/{teacherId}";
		}
	}	
}
