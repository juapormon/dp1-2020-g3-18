package org.springframework.samples.petclinic.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Score;
import org.springframework.samples.petclinic.model.Student;
import org.springframework.samples.petclinic.model.Subject;
import org.springframework.samples.petclinic.service.ScoreService;
import org.springframework.samples.petclinic.service.StudentService;
import org.springframework.samples.petclinic.service.SubjectService;
import org.springframework.samples.petclinic.service.TeacherService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class SubjectValidator implements Validator{
	
	private SubjectService subjectService;
	
	@Autowired
	public SubjectValidator(SubjectService subjectService) {
		this.subjectService = subjectService;
	}
	
	
	@Override
	public void validate(Object target, Errors errors) {
		Subject subject = (Subject) target;
		Collection<Subject> subjects = this.subjectService.findSubjects();
		
		if(subject.getCurso()>4 && subject.getCurso()<1) {
			errors.rejectValue("curso", "null curso", "Value must be between 1 and 4");
		}

	}
	
	

	@Override
	public boolean supports(Class<?> clazz) {
		return Subject.class.isAssignableFrom(clazz);
	}



}