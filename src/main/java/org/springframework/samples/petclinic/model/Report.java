package org.springframework.samples.petclinic.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reports")
@AllArgsConstructor @NoArgsConstructor
@Data
public class Report extends BaseEntity {

//@OneToOne
//private Pupil pupil;

	@NotEmpty
	@Column(name = "reason")
	private String reason;

}