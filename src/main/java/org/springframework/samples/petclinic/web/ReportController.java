package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Report;
import org.springframework.samples.petclinic.model.Reports;
import org.springframework.samples.petclinic.model.Score;
import org.springframework.samples.petclinic.model.Student;
import org.springframework.samples.petclinic.model.Teacher;
import org.springframework.samples.petclinic.model.Teachers;
import org.springframework.samples.petclinic.service.ReportService;
import org.springframework.samples.petclinic.service.ScoreService;
import org.springframework.samples.petclinic.service.StudentService;
import org.springframework.samples.petclinic.service.TeacherService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReportController {
	
	private static final String VIEW_REPORT_CREATE_FORM ="reports/createReport";
	
	private ScoreService scoreService;

	private ReportService reportService;

	@Autowired
	public ReportController(ScoreService scoreService, ReportService reportService) {
		this.scoreService = scoreService;
		this.reportService = reportService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	
	@GetMapping(value = {"/reports/new/{scoreId}"})
	public String initCreationForm(@PathVariable int scoreId, ModelMap model) {

		Score score = this.scoreService.findScoreById(scoreId);
		Report report = new Report();
		report.setScore(score);
		model.put("report", report);
		return VIEW_REPORT_CREATE_FORM;
	}	
	
	@PostMapping(value = {"/reports/new/{scoreId}"})
	public String processCreationForm(@PathVariable int scoreId, @Valid Report report, BindingResult result,
	ModelMap model) {
		if (result.hasErrors()) {
			model.put("report", report);
			return "reports/createReport";
		} else {
			Score score = this.scoreService.findScoreById(scoreId);
			report.setScore(score);
			this.reportService.saveReport(report);
			return "redirect:/reports";
		}
	}	
	
	@GetMapping(value = { "reports" })
	public String showReportList(Map<String, Object> model) {

		Reports reports = new Reports();
		reports.getReportsList().addAll(this.reportService.findReports());
		model.put("reports", reports);
		return "reports/reportsList";
	}
}

