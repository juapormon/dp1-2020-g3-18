package org.springframework.samples.petclinic.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "departments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Department extends BaseEntity{
	
	//Attributes
	@NotBlank
	private String 		name;
	
	
	//Relationships
	
	//IMPORTANTE LAS RELACIONES ABAJO ESCRITAS NO SON REALES, SOLO SE HAN ESCRITO PARA COMPROBAR SI EN EL H2-CONSOLE SE CREAN O NO TABLAS DE RELACION
	
//	@ManyToOne(optional=true)
//	private College college;
//	
//	@OneToMany
//	private Collection<Score> scores;
}
