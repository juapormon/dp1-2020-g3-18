package org.springframework.samples.petclinic.service;


import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Report;
import org.springframework.samples.petclinic.model.Teacher;
import org.springframework.samples.petclinic.repository.ReportRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReportService {
	
	private ReportRepository reportRepository;

	@Autowired
	public ReportService(ReportRepository reportRepository) {
		this.reportRepository = reportRepository;
	}
	
	@Transactional(readOnly = true)	
	public Report findReportById(int id){
		return reportRepository.findById(id);
	}
	
	
	@Transactional()	
	public void saveReport(@Valid Report report)  {
		reportRepository.save(report);
	}

	@Transactional(readOnly = true)
	public Collection<Report> findReports() throws DataAccessException {
		return reportRepository.findAll();
	}
}
