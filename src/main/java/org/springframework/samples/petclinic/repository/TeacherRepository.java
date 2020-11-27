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

	@Query("select t from Teacher t where t.subjects.contains.id=?1")
	Collection<Teacher> findBySubject(int i);
	
//	@Query("select t from Teacher t where t.id=?1")
//	Collection<Teacher> findBySubject(int i);
	
	//@Query("")
	//Collection<Teacher> showTeacherWithScore();
}
