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

	private Integer value;

	@NotBlank
	private String comment;
	
	//Relationships
	@ManyToOne(optional=true) 
	private Student student;
	
	@ManyToOne(optional=true)
	private Teacher teacher;
	



}
