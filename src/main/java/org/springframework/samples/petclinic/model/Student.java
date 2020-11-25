package org.springframework.samples.petclinic.model;


import java.util.List;

//import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
	
	@NotBlank
	private String name;
	
	@NotBlank
	@Email
	private String email;
	
	@ManyToMany
	private List<Subject> subjects;
	
	@ManyToMany
	private List<Teacher> teachers;

	
	
}
