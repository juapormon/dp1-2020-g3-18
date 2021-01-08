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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
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
		score.setValu(null);
		score.setComment("Este es tu comentario, cheñó");
		score.setStudent(new Student());
		score.setTeacher(new Teacher());
		Validator validator =  createValidator();
		Set<ConstraintViolation<Score>> constraintViolations = validator.validate(score);
		assertFalse(constraintViolations.isEmpty()); //por algun motivo falla
		
	}
//	private static ValidatorFactory validatorFactory;
//	private static javax.validation.Validator validator;
//
//	@BeforeClass
//	public static void createValidator() {
//		validatorFactory = Validation.buildDefaultValidatorFactory();
//		validator = validatorFactory.getValidator();
//	}
//	
//	@AfterClass
//	public static void close() {
//	    validatorFactory.close();
//
//	}
//	
//	@Test
//	void ShouldNotValidateWhenValueOutOfRange() {
//	    Score score = new Score();
//		score.setValu(7);
//		score.setComment("Este es tu comentario, cheñó");
//		score.setStudent(new Student());
//		score.setTeacher(new Teacher());
//		Set<ConstraintViolation<Score>> violations = validator.validate(score);
//		assertFalse(violations.isEmpty());
//	}
	
	
}
