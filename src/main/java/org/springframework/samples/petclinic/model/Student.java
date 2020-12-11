package org.springframework.samples.petclinic.model;


import java.util.Collection;

import javax.persistence.CascadeType;

//import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Pattern;

//import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.Setter;

@Data
@Entity
@Table(name = "students")
@Setter
public class Student extends Person{
	
	//Attributes
	@NotBlank
	private String name;
	
//	@Email
	private String email;
	
	//Relationships
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
	private User user;
	
	@ManyToMany (fetch = FetchType.LAZY)
	@JoinTable(name = "students_subjects", joinColumns = @JoinColumn(name = "student_id"),
	inverseJoinColumns = @JoinColumn(name = "subject_id"))
	private Collection<Subject> subjects;
	
	@ManyToMany 
	@JoinTable(name = "students_teachers", joinColumns = @JoinColumn(name = "student_id"),
	inverseJoinColumns = @JoinColumn(name = "teacher_id"))
	private Collection<Teacher> teachers;



	
}
