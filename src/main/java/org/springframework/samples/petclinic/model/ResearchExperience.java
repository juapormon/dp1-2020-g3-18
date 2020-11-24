package org.springframework.samples.petclinic.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "researchexperiences")
@AllArgsConstructor @NoArgsConstructor
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
