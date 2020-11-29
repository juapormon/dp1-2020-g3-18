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

}
