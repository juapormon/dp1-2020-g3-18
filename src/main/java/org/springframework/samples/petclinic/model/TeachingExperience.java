package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name ="teachingexperiences")
public class TeachingExperience extends BaseEntity{

	
	//Attributes
	@NotBlank
	private String titulation;
	

	private String comment;
	
	//Relationships



	
	
}
