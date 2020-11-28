package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "personalexperiences")
public class PersonalExperience extends BaseEntity{

	//Attributes
	
	
	//Relationships
	@OneToOne(optional = true)
	@JoinColumn(name = "res_experience")
	private ResearchExperience researchExperience;
	
	@OneToOne(optional = true)
	@JoinColumn(name = "tea_experience")
	private TeachingExperience teachingExperience;
	
	@OneToOne(optional = true)
	@JoinColumn(name = "pro_experience")
	private ProfessionalExperience professionalExperience;
	
	@OneToOne(optional = true)
	@JoinColumn(name = "ext_evaluation")
	private ExternalEvaluation externalEvaluation;

}
