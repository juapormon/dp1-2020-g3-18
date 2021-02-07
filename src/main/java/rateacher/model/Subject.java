package rateacher.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "subjects")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Subject extends BaseEntity {

	@NotBlank
	private String name;

	private Integer curso;

	@ManyToOne(optional = true)
	@JoinColumn(name = "department_id")
	private Department department;

	@ManyToMany
	private Collection<Teacher> teachers;

	@OneToOne(optional = true)
	@JoinColumn(name = "teaching_plan_id")
	private TeachingPlan teachingPlan;
}
