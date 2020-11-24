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
@Table(name ="teachingexperiences")
@AllArgsConstructor @NoArgsConstructor
public class TeachingExperience extends BaseEntity{

	@Column(name="titulation")
	@NotBlank
	private String titulation;
	
	@Column(name="comment")
	private String comment;

	public String getTitulation() {
		return titulation;
	}

	public void setTitulation(String titulation) {
		this.titulation = titulation;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
