package org.springframework.samples.petclinic.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Report;
import org.springframework.samples.petclinic.model.Score;
import org.springframework.samples.petclinic.model.Student;
import org.springframework.samples.petclinic.model.Teacher;
import org.springframework.samples.petclinic.service.ReportService;
import org.springframework.samples.petclinic.service.ScoreService;
import org.springframework.samples.petclinic.service.StudentService;
import org.springframework.samples.petclinic.service.TeacherService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ReportValidator implements Validator{
	
	private final ReportService reportService;

	
	@Autowired
	public ReportValidator(ReportService reportService) {
		this.reportService = reportService;
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		Report report = (Report) target;
		Collection<Report> reports = this.reportService.findReports();
		List<Score> scores = new ArrayList<>(); 
		Integer reportsOfTheSameScore = 0;
		for(Report r: reports) {
			scores.add(r.getScore());
		}
		if(scores.contains(report.getScore())) {
			reportsOfTheSameScore+=1;
		}
		if(reportsOfTheSameScore == 3) {
			errors.rejectValue("score", "too many reports", "There are already 3 reports made for this comment, you cannot report it again");
		}else { 
			
		if(report.getScore().getComment()==null || report.getScore().getComment().isEmpty()) {
			errors.rejectValue("reason", "no reason", "You must provide a valid reason for the report");
			}
		}
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Score.class.isAssignableFrom(clazz);
	}



}
