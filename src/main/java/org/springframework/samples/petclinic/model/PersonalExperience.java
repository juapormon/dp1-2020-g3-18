package org.springframework.samples.petclinic.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "personalexperiences")
@AllArgsConstructor @NoArgsConstructor
public class PersonalExperience extends BaseEntity{

	
	@ManyToOne(optional = true)
	@JoinColumn(name = "res_experience_id")
	private ResearchExperience researchExperience;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "tea_experience_id")
	private TeachingExperience teachingExperience;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "pro_experience_id")
	private ProfessionalExperience professionalExperience;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "ext_evaluation_id")
	private ExternalEvaluation externalEvaluation;

}
