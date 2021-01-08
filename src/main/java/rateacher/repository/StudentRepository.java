package rateacher.repository;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import rateacher.model.Student;
import rateacher.model.Subject;
import rateacher.model.Teacher;

public interface StudentRepository extends Repository<Student, Integer> {

	@Query("select s from Student s where s.id=?1")
	Student findById(int id) throws DataAccessException;

	Collection<Student> findAll() throws DataAccessException;

	void save(@Valid Student student) throws DataAccessException;

	@Query("select t from Teacher t")
	Collection<Teacher> teachersWithSameSubject();

	@Query("select s from Student s where scores_id is not null")
	Collection<Student> studentWithScore();

	@Query("select s from Student s where s.user.username = ?1")
	Student findStudentByUsername(String username);

	@Query("select s.subjects from Student s where s.id=?1")
	List<Subject> findMySubjects(int i);

	@Query("SELECT t FROM Teacher t  WHERE t.lastName || t.name LIKE :word%")
	public Collection<Teacher> findByWord(@Param("word") String word);

	@Query("select t from Teacher t where t.id in t.subjects AND t.subjects in"
			+ "(select s.subjects from Student s where s.id =?1)")
	Collection<Student> myTeachers(int id);

}