package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "teachingPlans")
public class TeachingPlan extends BaseEntity{

	@NotBlank
	private String 		name;
}
