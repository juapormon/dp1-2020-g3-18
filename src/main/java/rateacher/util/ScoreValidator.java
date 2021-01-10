package rateacher.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import rateacher.model.Score;
import rateacher.model.Student;
import rateacher.service.ScoreService;
import rateacher.service.StudentService;
import rateacher.service.TeacherService;

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

		if(score.getValu()==null) {
			errors.rejectValue("valu", "null score", "You cannot leave it blank");
		}else if(score.getValu()>5 || score.getValu()<0) {
			errors.rejectValue("valu", "bad range", "Value must be between 0 and 5");
		}
		if(score.getComment().equals("")) {
			errors.rejectValue("comment", "blank comment", "You cannot leave it blank");
		} 

	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Score.class.isAssignableFrom(clazz);
	}



}
