package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
@Table(name = "reports")
public class Report extends BaseEntity {

	//Attributes

	@NotBlank
	private String reason;
	
	//Relationships

	@OneToOne
	private Score score;
}
