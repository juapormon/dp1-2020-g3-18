package org.springframework.samples.petclinic.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Data
@Table(name = "teachers")
@AllArgsConstructor @NoArgsConstructor
public class Teacher extends Person{
	

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")

	private User user;
	
	@ManyToMany
	private List<College> colleges;
	
	@OneToOne
	private PersonalExperience personalExperience;
	
	@OneToOne
	private ProfessionalExperience professionalExperience;
	
	@OneToOne
	private ResearchExperience researchExperience;

	@OneToMany
	private List<Score> scores;
	
//	@OneToMany
//	private List<Subject> subjects;
}

