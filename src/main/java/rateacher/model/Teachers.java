package rateacher.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Teachers {

	

		private List<Teacher> teachers;

		@XmlElement
		public List<Teacher> getTeachersList() {
			if (teachers == null) {
				teachers = new ArrayList<>();
			}
			return teachers;
		

	}
}
