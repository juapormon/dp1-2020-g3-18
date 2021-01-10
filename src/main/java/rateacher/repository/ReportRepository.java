package rateacher.repository;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;

import rateacher.model.Report;

public interface ReportRepository extends Repository<Report, Integer>{

	Report findById(int id) throws DataAccessException;
	
	Collection<Report> findAll() throws DataAccessException;

	void save(@Valid Report report) throws DataAccessException;
	
}
