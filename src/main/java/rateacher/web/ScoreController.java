package rateacher.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import rateacher.model.Score;
import rateacher.service.ScoreService;

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


}
