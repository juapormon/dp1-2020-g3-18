package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Department;
import org.springframework.samples.petclinic.model.Subject;
import org.springframework.samples.petclinic.service.DepartmentService;
import org.springframework.samples.petclinic.service.SubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DepartmentController {

	private final DepartmentService departmentService;
	private final SubjectService subjectService;
	
	@Autowired
	public DepartmentController(DepartmentService departmentService, SubjectService subjectService) {
		this.departmentService = departmentService;
		this.subjectService	= subjectService;

	}
	
	@GetMapping(value = { "/departments" })
	public String showDepartmentList(Map<String, Object> model) {

		Collection<Department> departments = this.departmentService.findAll();
		model.put("departments", departments);
		
		
		return "departments/departmentsList";
		
	}
	
	@GetMapping(value = {"/departments/{departmentId}/relatedSubjects"})
	public String listSubjectsFromDepartmentId(@PathVariable("departmentId") int departmentId, Map<String, Object> model) {
		Department department = this.departmentService.findDepartmentById(departmentId);
		model.put("department", department);
		List<Subject> subjects = subjectService.findSubjectsFromDepartmentId(departmentId);
		model.put("subjects", subjects);
		return "departments/relatedSubjects";
	}
}
