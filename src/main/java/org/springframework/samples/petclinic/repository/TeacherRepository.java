package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Subject;
import org.springframework.samples.petclinic.model.Teacher;

public interface TeacherRepository extends Repository<Teacher, Integer>{

	Teacher findById(int id) throws DataAccessException;
	
	Collection<Teacher> findAll() throws DataAccessException;
	
	void save(Teacher teacher) throws DataAccessException;

	Collection<Teacher> findBySubject(Subject subject) throws DataAccessException;
	
	//@Query("")
	//Collection<Teacher> showTeacherWithScore();
}
