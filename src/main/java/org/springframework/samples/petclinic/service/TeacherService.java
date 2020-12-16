
package org.springframework.samples.petclinic.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Score;
import org.springframework.samples.petclinic.model.Student;
import org.springframework.samples.petclinic.model.Subject;
import org.springframework.samples.petclinic.model.Teacher;
import org.springframework.samples.petclinic.model.Teachers;
import org.springframework.samples.petclinic.repository.ScoreRepository;
import org.springframework.samples.petclinic.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeacherService {

	private TeacherRepository teacherRepository;
	private ScoreRepository scoreRepository;


	@Autowired
	private UserService userService;

	@Autowired
	private AuthoritiesService authoritiesService;

	@Autowired
	public TeacherService(TeacherRepository teacherRepository) { 
		this.teacherRepository = teacherRepository;
	}

	@Transactional(readOnly = true)
	public Teacher findTeacherById(int id) throws DataAccessException {
		Teacher teacher = teacherRepository.findById(id);
		assert teacher != null;
		return teacher;
	}

//	@Transactional(readOnly = true)
//	public Collection<Teacher> findTeacherBySubject(int i) throws DataAccessException {
//		return teacherRepository.findBySubject(i);
//	}

	@Transactional(readOnly = true)
	public Collection<Teacher> findTeachers() throws DataAccessException {
		return teacherRepository.findAll();
	}

	public Collection<Teacher> showTeacherWithScore() throws DataAccessException {
		return teacherRepository.showTeacherWithScore();
	}

	public Collection<Score> findScoresByTeacherId(int teacherId) throws DataAccessException {
		return teacherRepository.findScoresByTeacherId(teacherId);
	}

	@Transactional(readOnly = true)
	public Teacher findTeacherByLastName(String lastName) throws DataAccessException {
		return teacherRepository.findByLastName(lastName);
	}
	
	
	

	@Transactional
	public void saveTeacher(Teacher teacher) throws DataAccessException {
		// creating teacher
		teacherRepository.save(teacher);
		// creating user
		userService.saveUser(teacher.getUser());
		// creating authorities
		authoritiesService.saveAuthorities(teacher.getUser().getUsername(), "teacher");
	}
	
	//Buscar profesor por id de estudiante
	@Transactional(readOnly = true)
	public Collection<Teacher> findTeacherByStudentId(int studentId) throws DataAccessException {
		return teacherRepository.findByStudentId(studentId);
	}
	
	
	
	

}
