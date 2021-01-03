package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Subject;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class SubjectServiceTest {
	
	@Autowired
	protected SubjectService subjectService;
	
	@Autowired
	protected StudentService studentService;
	
	//Casos positivos
		@Test
		@DisplayName("Finding all subjects")
		void testFindAll() {

			Collection<Subject> res = this.subjectService.findSubjects();

			assertTrue(!res.isEmpty());
			

		}
		
		//Checkear
		@Test
		@DisplayName("Create subjects")
		void createSubject() {
			Collection<Subject> res = this.subjectService.findSubjects();
			Subject subject = new Subject();
			subject.setId(1);
			subject.setName("Cálculo Infinitesimal y Numérico");
			subject.setCurso(1);
			if (!res.contains(subject)) {
				res.add(subject);
			} 
			assertThat(res.size()+1);
		}
		
		//Checkear
		@SuppressWarnings("unlikely-arg-type")
		@Test
		@DisplayName("Delete subjects")
		void deleteSubject() {
			Collection<Subject> lista = this.subjectService.findSubjects();
			Optional<Subject> subject = this.subjectService.findSubjectById(1);
			if (lista.contains(subject)) {
				lista.remove(subject);
			}
			assertThat(lista.size()-1);
			
			
		}
		
		
		
//		@Test
//		@DisplayName("Finding subject by id")
//		void testFindStudentById(){
//			
//			Subject subject = this.subjectService.findSubjectById(111);
//			System.out.println(subject.getName());
//			assertThat(subject.getName()).startsWith("Diseño y pruebas");
//		}
//		
		

}