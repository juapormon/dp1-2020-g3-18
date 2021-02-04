package rateacher.tests.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import rateacher.model.Subject;
import rateacher.repository.SubjectRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SubjectRepositoryTest {
	
	@Autowired
	private SubjectRepository subjectRepository;

	@Test
	void findSubjectsFromDepartmentIdTest() {
	//arrange
	//act
		List<Subject> subjects = new ArrayList<>(subjectRepository.findSubjectsFromDepartmentId(1));//id de julian
	//assert	
		assertFalse(subjects.isEmpty());
		assertTrue(subjects.get(0).getName().equals("Diseño y pruebas"));
	}

}
