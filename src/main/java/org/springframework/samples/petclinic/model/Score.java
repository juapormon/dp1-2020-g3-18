package org.springframework.samples.petclinic.model;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "scores")
public class Score extends BaseEntity {

	//Attributes

	private Integer valu;

	@NotBlank
	private String comment;
	
	//Relationships
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;
	
	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;
	
	
	
	



}
