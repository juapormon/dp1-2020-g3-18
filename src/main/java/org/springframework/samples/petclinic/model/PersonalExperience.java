package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "personalExperiences")
public class PersonalExperience extends Person{
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "resExperience_id")
	private ResearchExperience researchExperience;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "teaExperience_id")
	private TeachingExperience teachingExperience;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "proExperience_id")
	private ProfessionalExperience professionalExperience;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "extEvaluation_id")
	private ExternalEvaluation externalEvaluation;

}
