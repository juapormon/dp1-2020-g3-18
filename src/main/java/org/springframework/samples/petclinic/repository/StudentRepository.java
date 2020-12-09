package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Student;
import org.springframework.samples.petclinic.model.Teacher;

public interface StudentRepository extends Repository<Student, Integer>{

	Student findById(int id) throws DataAccessException;

	Collection<Student> findAll() throws DataAccessException;

	void save(@Valid Student student) throws DataAccessException;
	

	//@Query("select t from Teacher t where t.subjects in (select s from Student s where s.subjects)")
	//Collection<Teacher> teachersWithSameSubject();
	//@Query("select s from Student s where scores_id is not null") 

//	@Query("select t from Teacher t where t.subjects in (select s from Student s where s.subjects)")
//	Collection<Teacher> teachersWithSameSubject();
	
	@Query("select t from Teacher t")
	Collection<Teacher> teachersWithSameSubject();
	
	@Query("select s from Student s where scores_id is not null")
	Collection<Student> studentWithScore();
	
	@Query("select s from Student s where s.user.username = ?1")
	Student findStudentByUsername(String username);

	//SELECT * FROM TEACHERS T WHERE T.ID IN 
	//( SELECT TEACHER_ID FROM TEACHERS_SUBJECTS TS WHERE TS.SUBJECT_ID IN 
	//(SELECT Subject_id from students_subjects ))
	
	@Query("select t from Teacher t where t.id in t.subjects AND t.subjects in"
			+ "(select s.subjects from Student s where s.id =?1)")
	Collection<Student> myTeachers(int id);
	
}
