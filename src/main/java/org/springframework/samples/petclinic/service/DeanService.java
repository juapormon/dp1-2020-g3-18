package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Teacher;
import org.springframework.samples.petclinic.repository.DeanRepository;
import org.springframework.samples.petclinic.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeanService {
	
	private TeacherRepository teacherRepository;
	private DeanRepository deanRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Autowired
	public DeanService(TeacherRepository teacherRepository, DeanRepository deanRepository) {
		this.deanRepository = deanRepository;
		this.teacherRepository = teacherRepository;
	}



	@Transactional(readOnly = true)
	public void saveTeacher(Teacher teacher) throws DataAccessException{
		teacherRepository.save(teacher);
		
	}
	
	@Transactional(readOnly = true)
	public Collection<Teacher> findAll() throws DataAccessException {
		return teacherRepository.findAll();
	}

}
