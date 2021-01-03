package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Reports{

	

		private List<Report> reports;

		@XmlElement
		public List<Report> getReportsList() {
			if (reports == null) {
				reports = new ArrayList<>();
			}
			return reports;
	}
}
