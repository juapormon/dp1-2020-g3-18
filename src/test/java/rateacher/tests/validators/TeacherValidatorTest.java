package rateacher.tests.validators;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;


import org.junit.jupiter.api.Test;

import org.springframework.context.i18n.LocaleContextHolder;

import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;



import rateacher.model.Teacher;

public class TeacherValidatorTest {
	
	
	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}
	

	@Test
	void ShouldNotValidateWhenFirstNameEmpty() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
	
		Teacher teacher = new Teacher();
		teacher.setFirstName("");
		teacher.setLastName("García");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Teacher>> constraintViolations = validator.validate(teacher);
		assertTrue(constraintViolations.size()==1);
		ConstraintViolation<Teacher> violation = constraintViolations.iterator().next();
		assertTrue(violation.getPropertyPath().toString().equals("firstName"));
		assertTrue(violation.getMessage().equals("must not be empty"));
	}

}
