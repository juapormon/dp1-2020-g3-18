package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Subject;
import org.springframework.samples.petclinic.repository.SubjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubjectService {
	@Autowired
	private SubjectRepository subjectRepository;

	public SubjectService(SubjectRepository subjectRepository) {
		this.subjectRepository = subjectRepository;
	}
	
	@Transactional(readOnly = true)	
	public Optional<Subject> findSubjectById(int id) throws DataAccessException {
		return subjectRepository.findById(id);
	}
	
	@Transactional(readOnly = true)	
	public Collection<Subject> findSubjects() throws DataAccessException {
		return subjectRepository.findAll();
	}
	@Transactional
	public void save(Subject subject) {
		subjectRepository.save(subject);
	}

	public void delete(Subject subject) {
		subjectRepository.delete(subject);
		
	}
	
	
	
	
}
