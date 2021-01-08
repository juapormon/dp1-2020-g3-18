package rateacher.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "deans")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
