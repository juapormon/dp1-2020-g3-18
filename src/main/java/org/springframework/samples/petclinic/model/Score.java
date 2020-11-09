package org.springframework.samples.petclinic.model;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Table;



@Entity
@Table(name = "scores")
public class Score extends BaseEntity {

	@Column(name = "point")
	private Integer point;

	@Column(name = "comment")
	private String comment;

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
