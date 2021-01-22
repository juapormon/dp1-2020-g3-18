package rateacher.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rateacher.model.PersonalExperience;
import rateacher.model.Student;
import rateacher.repository.PersonalExperienceRepository;
import rateacher.repository.StudentRepository;
import rateacher.repository.TeacherRepository;

@Service
public class PersonalExperienceService {

	private PersonalExperienceRepository personalExperienceRepository;
	
	@Autowired
	public PersonalExperienceService(PersonalExperienceRepository personalExperienceRepository) {
		this.personalExperienceRepository = personalExperienceRepository;

	}
	
	@Transactional(readOnly = true)
	public PersonalExperience findStudentById(int id) throws DataAccessException {
		PersonalExperience personalExperience = personalExperienceRepository.findById(id);
		assert personalExperience != null;
		return personalExperience;
	}

	@Transactional(readOnly = true)
	public Collection<PersonalExperience> findAllPersonalExperiences() throws DataAccessException {
		return personalExperienceRepository.findAll();
	}

	@Transactional
	public void saveStudent(PersonalExperience personalExperience) throws DataAccessException {
		// creating personalExperience
		personalExperienceRepository.save(personalExperience);
	}
}