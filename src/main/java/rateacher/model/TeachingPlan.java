package rateacher.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "teachingPlans")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeachingPlan extends BaseEntity{

	@NotBlank
	private String 		name;
}
