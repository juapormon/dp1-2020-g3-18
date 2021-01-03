package org.springframework.samples.petclinic.service;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Department;
import org.springframework.samples.petclinic.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepartmentService {

	private DepartmentRepository departmentRepository;

	@Autowired
	public DepartmentService(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}
	
	@Transactional(readOnly = true)	
	public Department findDepartmentById(int id) throws DataAccessException {
		return departmentRepository.findById(id);
	}
	
	
	@Transactional(readOnly = true)	
	public void saveDepartment(@Valid Department department) throws DataAccessException  {
		departmentRepository.save(department);
	}	
	
	public Collection<Department> findAll(){
		return this.departmentRepository.findAll();
	}
}
