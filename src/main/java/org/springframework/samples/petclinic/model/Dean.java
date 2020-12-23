package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "deans")
public class Dean extends Person {
	
	//Attributes
	
	
	//Relationships
	@OneToOne
    @JoinColumn(name = "username")
	private User user;
	
	@JoinColumn(name = "college_id")
	@OneToOne(optional=true) //Es posible que no haga falta el optional
	private College college;
	
	

	

}
