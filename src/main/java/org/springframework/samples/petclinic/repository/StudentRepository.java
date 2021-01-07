package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Student;
import org.springframework.samples.petclinic.model.Subject;
import org.springframework.samples.petclinic.model.Teacher;

public interface StudentRepository extends Repository<Student, Integer>{

	@Query("select s from Student s where s.id=?1")
	Student findById(int id) throws DataAccessException;

	Collection<Student> findAll() throws DataAccessException;

	void save(@Valid Student student) throws DataAccessException;
	

	//@Query("select t from Teacher t where t.subjects in (select s from Student s where s.subjects)")
	//Collection<Teacher> teachersWithSameSubject();
	//@Query("select s from Student s where scores_id is not null") 

//	@Query("select t from Teacher t where t.subjects in (select s from Student s where s.subjects)")
//	Collection<Teacher> teachersWithSameSubject();
	
	@Query("select t from Teacher t")
	Collection<Teacher> teachersWithSameSubject();
	
	@Query("select s from Student s where scores_id is not null")
	Collection<Student> studentWithScore();
	
	@Query("select s from Student s where s.user.username = ?1")
	Student findStudentByUsername(String username);
	
	@Query("select s.subjects from Student s where s.id=?1")
	List<Subject> findMySubjects(int i);

	//SELECT * FROM TEACHERS T WHERE T.ID IN 
	//( SELECT TEACHER_ID FROM TEACHERS_SUBJECTS TS WHERE TS.SUBJECT_ID IN 
	//(SELECT Subject_id from students_subjects ))   where s.id =?1
	
	@Query("SELECT t FROM Teacher t  WHERE t.lastName || t.name LIKE :word%")
	public Collection<Teacher> findByWord(@Param("word")String word); 
	
	@Query("select t from Teacher t where t.id in t.subjects AND t.subjects in"
			+ "(select s.subjects from Student s where s.id =?1)")
	Collection<Student> myTeachers(int id);
	
	@Query(nativeQuery = true, value = "SELECT * FROM STUDENTS s where s.id in "
			+ "(select student_id from scores ss where ss.teacher_id in "
			+ "(select t.id from teachers t where t.id=?1))")
	Collection<Student> StudentsRatedATeacher(int teacherId);

	
}
