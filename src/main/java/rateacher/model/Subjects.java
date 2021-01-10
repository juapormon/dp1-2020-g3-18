package rateacher.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Subjects {

		private List<Subject> subjects;

		@XmlElement
		public List<Subject> getSubjectList() {
			if (subjects == null) {
				subjects = new ArrayList<>();
			}
			return subjects;
		

	}
}
