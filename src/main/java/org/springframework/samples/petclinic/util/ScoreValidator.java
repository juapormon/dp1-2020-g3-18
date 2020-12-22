package org.springframework.samples.petclinic.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Score;
import org.springframework.samples.petclinic.model.Student;
import org.springframework.samples.petclinic.service.ScoreService;
import org.springframework.samples.petclinic.service.StudentService;
import org.springframework.samples.petclinic.service.TeacherService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ScoreValidator implements Validator{
	
	private final ScoreService scoreService;
	private final StudentService studentService;
	private final TeacherService teacherService;
	
	@Autowired
	public ScoreValidator(ScoreService scoreService, StudentService studentService, TeacherService teacherService) {
		this.scoreService = scoreService;
		this.studentService = studentService;
		this.teacherService = teacherService;
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		Score score = (Score) target;
		Collection<Score> scores = this.scoreService.findAll();
		List<Student> students = new ArrayList<>(); 
		String principal = SecurityContextHolder.getContext().getAuthentication().getName();
		Student student = this.studentService.findStudentByUsername(principal);
		score.setStudent(student);
		for(Score s: scores) {
			students.add(s.getStudent());
		}
		if(students.contains(score.getStudent())) {
			errors.rejectValue("comment", "duplicated score", "You already have a score on this teacher");
		}else if(score.getValu()==null) {
			errors.rejectValue("valu", "null score", "You cannot leave it blank");
		}else if(score.getValu()>5) {
			errors.rejectValue("valu", "bad range", "Value must be between 0 and 5");
		}

	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Score.class.isAssignableFrom(clazz);
	}



}
