package rateacher.tests.validators;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import rateacher.model.Score;
import rateacher.model.Student;
import rateacher.model.Teacher;


public class ScoreValidatorTest {

	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}
	
	@Test
	void ShouldNotValidateWhenValueOutOfRange() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Score score = new Score();
		score.setValu(20);
		score.setComment("Very good teacher, i like it.");
		score.setStudent(new Student());
		score.setTeacher(new Teacher());
		Validator validator =  createValidator();
		Set<ConstraintViolation<Score>> constraintViolations = validator.validate(score);
		assertTrue(constraintViolations.size()==1);
		ConstraintViolation<Score> violation = constraintViolations.iterator().next();
		assertTrue(violation.getPropertyPath().toString().equals("valu"));
		assertTrue(violation.getMessage().equals("must be between 1 and 5"));
		
		
		
		
	}

	
	
}
