package org.springframework.samples.petclinic.web;

//import javax.validation.Valid;
//
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.samples.petclinic.model.Score;
//import org.springframework.samples.petclinic.model.Teacher;
//import org.springframework.samples.petclinic.service.ScoreService;
//import org.springframework.samples.petclinic.service.TeacherService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.InitBinder;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class ScoreController {

//	private final ScoreService scoreService;
//	private final TeacherService teacherService;
//
//	@Autowired
//	public ScoreController(ScoreService clinicService, TeacherService teacherService) {
//		this.scoreService = clinicService;
//		this.teacherService = teacherService;
//	}
//	
//	@InitBinder
//	public void setAllowedFields(WebDataBinder dataBinder) {
//		dataBinder.setDisallowedFields("id");
//	}
//	
//	@ModelAttribute("score")
//	public Score findScore(@PathVariable("scoreId") int scoreId) {
//		return this.scoreService.findScoreById(scoreId);
//	}
//	
//
//}
