package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Score;
import org.springframework.samples.petclinic.model.Student;
import org.springframework.samples.petclinic.model.Subject;

import org.springframework.samples.petclinic.model.Teacher;

public interface TeacherRepository extends Repository<Teacher, Integer>{

	Teacher findById(int id) throws DataAccessException;
	
	Collection<Teacher> findAll() throws DataAccessException;
	
	void save(Teacher teacher) throws DataAccessException; 
	//"SELECT DISTINCT owner FROM Owner owner left join fetch owner.pets WHERE owner.lastName LIKE :lastName%"
	@Query("SELECT t FROM Teacher t  WHERE t.lastName LIKE :lastName%")
	public Collection<Teacher> findByLastName(@Param("lastName") String lastName);


	@Query("select t from Teacher t  where t.id in (select teacher from Score s where s.teacher is not null )")
	Collection<Teacher> showTeacherWithScore();

//	@Query("select t from Teacher t where t.subjects.contains.id=?1")
//	Collection<Teacher> findBySubject(int i);
	//TODO: Esta query hay que ponerla bien, solo borrar la de aqui abajo y poner la query bien
	//La query de abajo esta solo para que no pete.
	
	@Query("select t from Teacher t where t.id=?1")
	Collection<Teacher> findBySubject(int i);
	
	//@Query("")
	//Collection<Teacher> showTeacherWithScore();
	
	@Query("select s from Score s where s.teacher.id = ?1") 
	Collection<Score> findScoresByTeacherId(int id);
	

}
