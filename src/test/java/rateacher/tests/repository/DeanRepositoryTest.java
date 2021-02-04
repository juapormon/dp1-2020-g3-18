package rateacher.tests.repository;

import static org.junit.Assert.assertTrue;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import rateacher.model.Dean;
import rateacher.repository.DeanRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class DeanRepositoryTest {

	@Autowired
	private DeanRepository deanRepository;

	@Test
	void findDeanByUsernameTest() {
	//arrange
	//act
		Dean dean = deanRepository.findDeanByUsername("a");//id de julian
	//assert	
		assertTrue(dean.getFirstName().equals("Paco"));
	}
}
