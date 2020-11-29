package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "colleges")
public class College extends BaseEntity{
	
	//Attributes 
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String city;
	
	//Relationships
	
	//TODO: Esta relación no deberia estar
//	@ManyToOne(optional = true) 
//	private Dean dean;

	
	

}
