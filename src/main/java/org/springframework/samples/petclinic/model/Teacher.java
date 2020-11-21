package org.springframework.samples.petclinic.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "teachers")
public class Teacher extends Person{
	

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")

	@Column(name = "user")
	private User user;
	
	@Column(name = "college")
	private College college;
	
	@Column(name = "personal_experience")
	private PersonalExperience personalExperience;
	
	@Column(name = "professional_experience")
	private ProfessionalExperience professionalExperience;

	
	@Column(name = "research_experience")
	private ResearchExperience researchExperience;

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public College getCollege() {
		return college;
	}
	
	public void setCollege(College college) {
		this.college = college;
	}
	
	public PersonalExperience getPersonalExperience() {
		return personalExperience;
	}

	public void setPersonalExperience(PersonalExperience personalExperience) {
		this.personalExperience = personalExperience;
	}
	
	public ProfessionalExperience getProfessionalExperience() {
		return professionalExperience;
	}

	public void setProfessionalExperience(ProfessionalExperience professionalExperience) {
		this.professionalExperience = professionalExperience;
	}
	
	public ResearchExperience getResearchExperience() {
		return researchExperience;
	}

	public void setResearchExperience(ResearchExperience researchExperience) {
		this.researchExperience = researchExperience;
	}

}
