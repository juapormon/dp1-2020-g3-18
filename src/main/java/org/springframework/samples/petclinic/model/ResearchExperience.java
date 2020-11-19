package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "researchexperiences")

public class ResearchExperience extends BaseEntity{
	
	@Column(name ="research")
	@NotBlank
	private String research;

	public String getResearch() {
		return research;
	}

	public void setResearch(String research) {
		this.research = research;
	}
	
	

}