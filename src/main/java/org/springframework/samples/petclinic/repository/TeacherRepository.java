package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Teacher;

public interface TeacherRepository extends Repository<Teacher, Integer>{

	Teacher findById(int id) throws DataAccessException;
	
	Collection<Teacher> findAll() throws DataAccessException;
	
	void save(Teacher teacher) throws DataAccessException;

//	@Query("SELECT T.FIRST_NAME, T.LAST_NAME FROM TEACHERS T WHERE T.ID IN (SELECT S.TEACHERS_ID FROM TEACHER_SCORES  S)")
//	@Query("select t from Teacher t where t.id in (select t1 from Teacher t1 where t1.scores is not null)") 
//	Collection<Teacher> showTeacherWithScore(); 
	

//	Query("select a from Authenticated a where a in 
	//(select p.authenticated from Participation p where p.thread.id = ?1) 
	//or a in (select thr.authenticated from Thread thr where thr.id = ?1)
	//select s from Student s where scores_id is not null
	@Query("select t from Teacher t  where t.id in (select teacher from Score s where s.teacher is not null )")
	Collection<Teacher> showTeacherWithScore();

	@Query("select comment from Score s where s.teacher.id= ?1 ")
	Collection<String> findCommentById(int id) throws DataAccessException;
}
