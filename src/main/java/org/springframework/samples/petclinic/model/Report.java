package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "reports")
@AllArgsConstructor @NoArgsConstructor
public class Report extends BaseEntity {

//@OneToOne
//private Pupil pupil;

	@NotEmpty
	@Column(name = "reason")
	private String reason;

}
