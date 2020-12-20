package org.springframework.samples.petclinic.util;

public class DuplicatedStudentScoreException extends Exception{
  
	public DuplicatedStudentScoreException(String errorMessage) {
        super(errorMessage);
    }

}
