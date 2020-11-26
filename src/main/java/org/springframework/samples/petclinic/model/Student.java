package org.springframework.samples.petclinic.model;


import java.util.List;

import javax.persistence.CascadeType;

//import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;

//import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "students")
@AllArgsConstructor @NoArgsConstructor
public class Student extends Person{
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
	private User user;
	
	@ManyToMany
	private List<Subject> subjects;
	
	@ManyToMany
	private List<Teacher> teachers;

	@ManyToOne(optional = true)
	@JoinColumn(name = "scores_id")
	private Score scores;

	
}
