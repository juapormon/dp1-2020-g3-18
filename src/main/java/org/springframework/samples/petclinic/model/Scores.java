package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Scores {

		private Collection<Score> scores;

		@XmlElement
		public Collection<Score> getScoreList() {
			if (scores == null) {
				scores = new ArrayList<>();
			}
			return scores;
		}
		
		@XmlElement
		public List<String> getCommentList() {
			List<String> res = new ArrayList<>();
			if (scores == null) {
				scores = new ArrayList<>();
			}else {
				for (Score r : scores) {
					res.add(r.getComment());
				}
			}
			return res;
			

		}
		
		@XmlElement
		public Integer getTotalScores() {
			return getScoreList().size();
		}
}
