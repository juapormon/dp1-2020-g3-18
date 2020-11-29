package org.springframework.samples.petclinic.model;


import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;

//import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Pattern;

//import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "students")
public class Student extends Person{
	
	//Attributes
	@NotBlank
	private String name;
	
	@Email
	private String email;
	
	//Relationships
	@OneToOne
    @JoinColumn(name = "username")
	private User user;
	
//	@ManyToMany
//	private Collection<Subject> subjects;
	
	@ManyToMany
	@Column(name = "subjects")
	private Collection<Subject> subject;
	
	@OneToMany
	private Collection<Teacher> teachers;



	
}
