package org.springframework.samples.petclinic.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "colleges")
@AllArgsConstructor @NoArgsConstructor
public class College extends BaseEntity{
	
	@Column(name = "name")
	@NotEmpty
	private String name;
	
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
