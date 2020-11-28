package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "departments")
public class Department extends BaseEntity{
	
	//Attributes
	@NotBlank
	private String 		name;
	
	
	//Relationships
	
}
