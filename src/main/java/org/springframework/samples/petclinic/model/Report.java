package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;


@Entity
@Data

@Table(name = "report")
public class Report extends BaseEntity{
	
//@OneToOne
//private Pupil pupil;

@NotEmpty
private String reason;
	
}
