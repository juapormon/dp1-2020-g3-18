package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "researchexperiences")
public class ResearchExperience extends BaseEntity{
	
	//Attributes
	@NotBlank
	private String research;

	
	
	//Relationships

}
