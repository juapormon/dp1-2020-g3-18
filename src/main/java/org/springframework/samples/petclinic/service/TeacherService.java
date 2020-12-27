
package org.springframework.samples.petclinic.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Department;
import org.springframework.samples.petclinic.model.Score;
import org.springframework.samples.petclinic.model.Student;
import org.springframework.samples.petclinic.model.Subject;
import org.springframework.samples.petclinic.model.Teacher;
import org.springframework.samples.petclinic.repository.DepartmentRepository;
import org.springframework.samples.petclinic.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeacherService {

	private TeacherRepository teacherRepository;
	private DepartmentRepository departmentRepository;

	private UserService userService;

	private AuthoritiesService authoritiesService;

	@Autowired
	public TeacherService(TeacherRepository teacherRepository, UserService userService, AuthoritiesService authoritiesService) { 
		this.teacherRepository = teacherRepository;
		this.userService = userService;
		this.authoritiesService = authoritiesService;
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
	public List<Teacher> findTeacherByFirstName(String firstName) throws DataAccessException {
		return teacherRepository.findByFirstName(firstName);
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
	
	@Transactional	
	public List<Teacher> findTeachersFromDepartment(int departmentId) throws DataAccessException {
		//Tengo un departament mame y quiero obtener la lista de teachers que tienen este department
		Department d = departmentRepository.findById(departmentId); 
		List<Teacher> result = new ArrayList<Teacher>();
		for(Teacher t2 : teacherRepository.findAll()) {
			if(t2.getDepartments().contains(d)) result.add(t2);
		}
		return result;
	}

}
