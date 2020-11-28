package org.springframework.samples.petclinic.model;


import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.core.style.ToStringCreator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "teachers")
@AllArgsConstructor @NoArgsConstructor
public class Teacher extends Person{
	

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")

	private User user;
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
	private Collection<Score> scores;
	
	
	@ManyToMany (cascade = CascadeType.ALL)
//	@JoinTable(name = "teacher_colleges", joinColumns = @JoinColumn(name = "teachers_id"),
//	inverseJoinColumns = @JoinColumn(name = "colleges_id"))
	private List<College> colleges;
	
	@OneToOne (optional = true)
	private PersonalExperience personalExperience;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private Collection<Subject> subjects; 
	
	
	protected Collection<Score> getScoresInternal() {
		if (this.scores == null) {
			this.scores = new HashSet<>();
		}
		return this.scores;
	}
	
	public void addScore(Score score) {
		getScoresInternal().add(score);
		score.setTeacher(this);
	}



}