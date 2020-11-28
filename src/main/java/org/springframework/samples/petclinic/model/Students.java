package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Students {

		private List<Student> students;

		@XmlElement
		public List<Student> getStudentList() {
			if (students == null) {
				students = new ArrayList<>();
			}
			return students;
		

	}
}
