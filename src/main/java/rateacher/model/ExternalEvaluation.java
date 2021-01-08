package rateacher.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "externalevaluations")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExternalEvaluation extends BaseEntity{

	//Attributes
	@NotNull
	private Integer note;
	
	@NotBlank
	private String evaluationReport;
	
	@NotBlank
	private String comment;
	


	
	
}
