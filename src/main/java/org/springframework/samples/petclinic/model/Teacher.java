package org.springframework.samples.petclinic.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "teachers")
@AllArgsConstructor @NoArgsConstructor
public class Teacher extends Person{
	

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")

	private User user;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
	private Set<Score> scores;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	protected Set<Score> getScoresInternal() {
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
