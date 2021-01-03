package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.Optional;
import java.util.List;


import javax.validation.Valid;

import org.springframework.dao.DataAccessException;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.samples.petclinic.model.Subject;

public interface SubjectRepository extends CrudRepository<Subject, Integer>{
	
	Optional<Subject> findById(int id) throws DataAccessException;
	
	Collection<Subject> findAll() throws DataAccessException;
	
	@SuppressWarnings("unchecked")
	Subject save(@Valid Subject entity) throws DataAccessException;

	
//	@Query("select s from Subject s where s.department.id = ?1 ")
//	List<Subject> findSubjectsFromDepartmentId(int idDepartment);
	
//	@Query("select s from Subject s in (select d from Department d where d=?1) ")
//	List<Subject> findSubjectsFromDepartmentId(int idDepartment);
	
	@Query("select s from Subject s")
	List<Subject> findSubjectsFromDepartmentId(int idDepartment);


}
