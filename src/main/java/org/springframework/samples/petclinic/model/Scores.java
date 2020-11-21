package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Scores {

		private List<Score> scores;

		@XmlElement
		public List<Score> getScoreList() {
			if (scores == null) {
				scores = new ArrayList<>();
			}
			return scores;
		

	}
}
