package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "externalevaluations")
public class ExternalEvaluation extends BaseEntity{

	//Attributes
	@NotNull
	private Integer note;
	
	@NotBlank
	private String evaluationReport;
	
	@NotBlank
	private String comment;
	
	//Relationships


	
	
}
