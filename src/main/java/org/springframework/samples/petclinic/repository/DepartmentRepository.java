package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Department;


public interface DepartmentRepository extends Repository<Department, Integer>{


		Department findById(int id) throws DataAccessException;
		
		Collection<Department> findAll() throws DataAccessException;

		void save(@Valid Department department) throws DataAccessException;
}
