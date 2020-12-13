package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Student;
import org.springframework.samples.petclinic.model.Subject;
import org.springframework.samples.petclinic.model.Teacher;
import org.springframework.samples.petclinic.repository.StudentRepository;
import org.springframework.samples.petclinic.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

	private StudentRepository studentRepository;
	private TeacherRepository teacherRepository;


	@Autowired
	private UserService userService;

	@Autowired
	private AuthoritiesService authoritiesService;

	@Autowired
	public StudentService(StudentRepository studentRepository, TeacherRepository teacherRepository) {
		this.studentRepository = studentRepository;
		this.teacherRepository = teacherRepository;

	}

	@Transactional(readOnly = true)
	public Student findStudentById(int id) throws DataAccessException {
		Student student = studentRepository.findById(id);
		assert student != null;
		return student;
	}

	@Transactional(readOnly = true)
	public Collection<Student> findStudents() throws DataAccessException {
		return studentRepository.findAll();
	}

	public Collection<Student> studentWithScore() throws DataAccessException {
		return studentRepository.studentWithScore();
	}

	@Transactional
	public void saveStudent(Student student) throws DataAccessException {
		// creating student
		studentRepository.save(student);
		// creating user
		userService.saveUser(student.getUser());
		// creating authorities
		authoritiesService.saveAuthorities(student.getUser().getUsername(), "student");
	}
	
	@Transactional(readOnly = true)	
	public Collection<Teacher> findTeachersBySubject(int i) throws DataAccessException {
		return teacherRepository.findBySubject(i);
	}
	
	@Transactional(readOnly = true)
	public Student findStudentByUsername(String username) throws DataAccessException {
		return studentRepository.findStudentByUsername(username);
	}
	
	@Transactional
	public List<Subject> findMySubjects(int i){
		return studentRepository.findMySubjects(i);
	}


	public Collection<Student> myTeachers(int id) throws DataAccessException{
		return studentRepository.myTeachers(id);
	}

	
}
