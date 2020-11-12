package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "colleges")
public class College extends NamedEntity{
	
	@Column(name = "ciudad")
	@NotEmpty
	private String ciudad;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "dean_id")
	private Dean dean;

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	

}
