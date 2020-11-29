package org.springframework.samples.petclinic.model;


import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "teachers")
public class Teacher extends Person{
	
	//Attributes
	@NotBlank
	private String name;
	
	//Relationships
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
	private User user;
	
	@ManyToMany
	private Collection<College> colleges;
	
	@OneToOne (optional = true)
	private PersonalExperience personalExperience;
	
	@ManyToMany
	private Collection<Department> departments;
	
	@ManyToMany
	private Collection<Subject> subjects;
	
	



}