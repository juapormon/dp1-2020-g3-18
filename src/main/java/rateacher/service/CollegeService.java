package rateacher.service;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rateacher.model.College;
import rateacher.model.Dean;
import rateacher.model.Score;
import rateacher.model.Teacher;
import rateacher.repository.CollegeRepository;
import rateacher.repository.DeanRepository;
import rateacher.repository.TeacherRepository;

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
