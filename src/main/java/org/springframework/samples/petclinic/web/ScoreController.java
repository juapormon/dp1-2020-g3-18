package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Score;
import org.springframework.samples.petclinic.model.Scores;
import org.springframework.samples.petclinic.service.ScoreService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ScoreController {

	private final ScoreService scoreService;

	@Autowired
	public ScoreController(ScoreService clinicService) {
		this.scoreService = clinicService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
//	@ModelAttribute("score")
//	public Score findOwner(@PathVariable("scoreId") int scoreId) {
//		return this.scoreService.findScoreById(scoreId);
//	}

	@GetMapping(value = { "/scores" })
	public String showScoreList(Map<String, Object> model) {
		
		Scores scores = new Scores();
		scores.getScoreList().addAll(this.scoreService.findScores());
		model.put("scores", scores);
		return "scores/scoresList";
	}
}
