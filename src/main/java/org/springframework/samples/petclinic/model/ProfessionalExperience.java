package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "professionalexperiences")

public class ProfessionalExperience extends Person{

	@Column(name ="university")
	@NotBlank
	private String university;
	

	@Column(name ="comment")
	private String comment;


	public String getUniversity() {
		return university;
	}


	public void setUniversity(String university) {
		this.university = university;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}
	

	
}
