package org.springframework.samples.petclinic.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "subjects")
public class Subject extends BaseEntity{

	@NotBlank
	private String		name;
	
	private Integer		curso;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<Department> departments;
//	
//	@ManyToOne (fetch = FetchType.EAGER)
//    @JoinTable(name = "subjects_departments", joinColumns = @JoinColumn(name = "subject_id"),
//    inverseJoinColumns = @JoinColumn(name = "department_id"))
//    private Department department;
}
