package org.springframework.samples.petclinic.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Dean;
import org.springframework.samples.petclinic.model.Teacher;

public interface DeanRepository extends Repository<Dean, Integer>{
	//void save (Teacher teacher) throws DataAccessException;
}
