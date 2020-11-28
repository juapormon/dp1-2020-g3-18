
package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Score;
import org.springframework.samples.petclinic.model.Teacher;
import org.springframework.samples.petclinic.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeacherService {

	
	private TeacherRepository teacherRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Autowired
	public TeacherService(TeacherRepository teacherRepository) {
		this.teacherRepository = teacherRepository;
	}	

	@Transactional(readOnly = true)
	public Teacher findTeacherById(int id) throws IllegalArgumentException {
		return teacherRepository.findById(id);
	}
	
	@Transactional(readOnly = true)	
	public Collection<Teacher> findTeachers() throws IllegalArgumentException {
		return teacherRepository.findAll();
	}
	@Transactional(readOnly = true)
	public Collection<Teacher> showTeacherWithScore() throws IllegalArgumentException{
		return teacherRepository.showTeacherWithScore();
	}

	@Transactional
	public void saveTeacher(Teacher teacher) throws IllegalArgumentException {
		//creating teacher
		teacherRepository.save(teacher);		
		//creating user
		userService.saveUser(teacher.getUser());
		//creating authorities
		authoritiesService.saveAuthorities(teacher.getUser().getUsername(), "teacher");
	}	
	
	@Transactional(readOnly = true)	
	public Collection<String> findTeacherCommentById(int id) throws IllegalArgumentException {
		return teacherRepository.findCommentById(id);
	}
	
}

