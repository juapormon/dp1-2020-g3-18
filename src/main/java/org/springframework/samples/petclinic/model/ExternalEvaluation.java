package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;


@Entity
@Table(name = "externalEvaluations")
public class ExternalEvaluation extends Person{

	@Column(name = "note")
	@NotNull
	private Integer note;
	
	@Column(name ="evaluationReport")
	@NotBlank
	private String evaluationReport;
	
	@Column(name="comment")
	private String comment;

	public Integer getNote() {
		return note;
	}

	public void setNote(Integer note) {
		this.note = note;
	}

	public String getEvaluationReport() {
		return evaluationReport;
	}

	public void setEvaluationReport(String evaluationReport) {
		this.evaluationReport = evaluationReport;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
