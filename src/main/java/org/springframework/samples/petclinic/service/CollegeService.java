package org.springframework.samples.petclinic.service;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.College;
import org.springframework.samples.petclinic.model.Dean;
import org.springframework.samples.petclinic.model.Score;
import org.springframework.samples.petclinic.model.Teacher;
import org.springframework.samples.petclinic.repository.CollegeRepository;
import org.springframework.samples.petclinic.repository.DeanRepository;
import org.springframework.samples.petclinic.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CollegeService {
	
	private CollegeRepository collegeRepository;

	
	@Autowired
	public CollegeService(CollegeRepository collegeRepository) {
		this.collegeRepository = collegeRepository;
	}
	
	@Transactional(readOnly = true)
	public College findCollegeById(int id) throws DataAccessException {
		return collegeRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public Collection<College> findAllColleges() throws DataAccessException {
		return collegeRepository.findAll();
	}
	
	@Transactional(readOnly = false)	
	public void saveCollege(@Valid College college)  {
		collegeRepository.save(college);
	}	

}
