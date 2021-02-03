package rateacher.tests.validators;
import static org.junit.Assert.assertTrue;
import java.util.Locale;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.context.i18n.LocaleContextHolder;
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
		System.out.println("---------------------------------------------");
		System.out.println("Should not validate when value out of range");
		System.out.println("---------------------------------------------");
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
	
	@Test
	void ShouldNotValidateWhenValueEmpty() {
		System.out.println("---------------------------------------------");
		System.out.println("Should not validate when value is empty");
		System.out.println("---------------------------------------------");
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Score score1 = new Score();
		score1.setValu(null);
		score1.setComment("Este es un comentario de prueba para el test");
		Validator validator = createValidator();
		Set<ConstraintViolation<Score>> constraintViolations1 = validator.validate(score1);
		assertTrue(constraintViolations1.size()==1);
		ConstraintViolation<Score> violation1 = constraintViolations1.iterator().next();
		assertTrue(violation1.getPropertyPath().toString().equals("valu"));
		assertTrue(violation1.getMessage().equals("must not be null"));
		
	}
	
	@Test
	void ShouldNotValidateWhenCommentEmpty() {
		System.out.println("---------------------------------------------");
		System.out.println("Should not validate when comment is null");
		System.out.println("---------------------------------------------");
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Score score1 = new Score();
		score1.setValu(3);
		score1.setComment("");
		Validator validator = createValidator();
		Set<ConstraintViolation<Score>> constraintViolations1 = validator.validate(score1);
		assertTrue(constraintViolations1.size()==1);
		ConstraintViolation<Score> violation1 = constraintViolations1.iterator().next();
		assertTrue(violation1.getPropertyPath().toString().equals("comment"));
		assertTrue(violation1.getMessage().equals("must not be empty"));
		
	}
	

	


	
	
}
