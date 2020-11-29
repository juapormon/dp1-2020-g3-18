package org.springframework.samples.petclinic.model;


import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
	@OneToOne
    @JoinColumn(name = "username")
	private User user;
	
	@ManyToMany
	private Collection<College> colleges;
	
	@OneToOne (optional = true)
	private PersonalExperience personalExperience;
	
	@ManyToMany
	private Collection<Department> departments;
	
	@OneToMany
   // @JoinColumn(name = "ABC", referencedColumnName="name")
	@JoinColumn(name="ABC")
	private Collection<Subject> cosasraras;
	
	



}