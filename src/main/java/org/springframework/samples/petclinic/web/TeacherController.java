
package org.springframework.samples.petclinic.web;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Score;
import org.springframework.samples.petclinic.model.Student;
import org.springframework.samples.petclinic.model.Teacher;
import org.springframework.samples.petclinic.model.Teachers;
import org.springframework.samples.petclinic.repository.ScoreRepository;
import org.springframework.samples.petclinic.service.ScoreService;
import org.springframework.samples.petclinic.service.StudentService;
import org.springframework.samples.petclinic.service.TeacherService;
import org.springframework.samples.petclinic.util.ScoreValidator;
import org.springframework.samples.petclinic.util.DuplicatedStudentScoreException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.BeanUtils;

@Controller
public class TeacherController {

	private final TeacherService teacherService;
	private final StudentService studentService;
	private final ScoreService scoreService;

	@Autowired
	public TeacherController(TeacherService teacherService, StudentService studentService, ScoreService scoreService) {
		this.teacherService = teacherService;
		this.studentService = studentService;
		this.scoreService = scoreService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@InitBinder("score")
	public void initTeacherBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new ScoreValidator(scoreService, studentService, teacherService));
	}

	@GetMapping(value = { "teachers" })
	public String showTeacherList(Map<String, Object> model) {

		Teachers teachers = new Teachers();
		teachers.getTeachersList().addAll(this.teacherService.findTeachers());
		model.put("teachers", teachers);
		return "teachers/teachersList";

	}

	@GetMapping(value = { "/teachers.xml" })
	public @ResponseBody Teachers showResourcesTeacherList() {

		Teachers teachers = new Teachers();
		teachers.getTeachersList().addAll(this.teacherService.findTeachers());
		return teachers;
	}

	@GetMapping(value = "teachers/{teacherId}")
	public ModelAndView showTeacher(@PathVariable("teacherId") int teacherId, Map<String, Object> model) {
		ModelAndView mav = new ModelAndView("teachers/teacherDetails");
		mav.addObject(this.teacherService.findTeacherById(teacherId));
		return mav;
	}

	@GetMapping(value = { "/teachersWithScore" })
	public String showTeacherWithScore(Map<String, Object> model) {

		Teachers teachers = new Teachers();
		teachers.getTeachersList().addAll(this.teacherService.showTeacherWithScore());
		model.put("teachers", teachers);
		return "teachers/teachersWithScore";

	}

	@GetMapping(value = "/findTeachers")
	public String initFindForm(Map<String, Object> model) {
		model.put("teachers", new Teacher());
		return "teachers/findTeachers";
	}

	@GetMapping(value = "/teachersFound")
	public String processFindForm(Teacher teacher, BindingResult result, Map<String, Object> model) {


		if (teacher.getFirstName() == null) {
			teacher.setFirstName(""); // empty string signifies broadest possible search
		}
		// find teachers by first name
		List<Teacher> results = this.teacherService.findTeacherByFirstName(teacher.getFirstName());
		if (results.isEmpty()) {
			result.rejectValue("firstName", "notFound", "not found");
			model.put("teachers", new Teacher());
			return "teachers/findTeachers";
		}
		else if (results.size() == 1) {
			// 1 teacher found
			 teacher = results.iterator().next();
			return "redirect:/teachers/" + teacher.getId();
		}
		else {
			// multiple teachers found
			model.put("selections", results);
			return "teachers/teachersList";
		}
	}

	@GetMapping(value = { "teachers/{teacherId}/scores" })
	public String showTeacherScoreList(@PathVariable("teacherId") int teacherId, Map<String, Object> model) {
		Collection<Score> scores = this.teacherService.findScoresByTeacherId(teacherId);
		model.put("scores", scores);
		return "scores/scoresList";
	}

//	@GetMapping(value = { "/teachers/{teacherId}/scores/comments" })  
//	public String showTeacherCommentList(Teacher teacher, Map<String, Object> model) {
//		List<String> comments = new ArrayList<>();
//		Scores scores = new Scores();
//		comments.addAll(this.scoreService.findTeacherCommentById(teacher.getId()));
//		model.put("comments", comments);
//		model.put("scores", scores);
//		return "scores/teacherCommentList";
//	}

	@GetMapping(value = { "teachers/{teacherId}/scores/new" })
	public String initCreationForm(@PathVariable int teacherId, ModelMap model){ // para crear el modelo que va a la vista.
		Score score = new Score();
		Teacher teacher = this.teacherService.findTeacherById(teacherId);
		score.setTeacher(teacher);
		model.put("score", score);
		model.put("teacher", teacher);
		return "scores/createForm";
	}

	@PostMapping(value = "teachers/{teacherId}/scores/new")
	public String processCreationForm(@PathVariable int teacherId, @Valid Score score, BindingResult result,
			ModelMap model) {
		if (result.hasErrors()) {
			model.put("score", score);
			Teacher teacher = teacherService.findTeacherById(teacherId);
			model.put("teacher", teacher);
			return "scores/createForm";
		} else {
			String principal = SecurityContextHolder.getContext().getAuthentication().getName();
			Student student = this.studentService.findStudentByUsername(principal);
			score.setStudent(student);
			Teacher teacher = this.teacherService.findTeacherById(teacherId);
			score.setTeacher(teacher);
			this.scoreService.saveScore2Create(score, teacherId);
			return "redirect:/teachers/{teacherId}/scores";
		}
	}

	@GetMapping(value = "teachers/{teacherId}/scores/{scoreId}/edit")
	public String initEditForm(@PathVariable int teacherId, @PathVariable int scoreId, ModelMap model) {
		Score score = this.scoreService.findScoreById(scoreId);
		model.put("score", score);
		Teacher teacher = this.teacherService.findTeacherById(teacherId);
		model.put("teacher", teacher);
		return "scores/createForm";
	}

	@PostMapping(value = "teachers/{teacherId}/scores/{scoreId}/edit")
	public String processEditForm(@PathVariable int teacherId, @PathVariable int scoreId, @Valid Score score,
			BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			Teacher teacher = this.teacherService.findTeacherById(teacherId);
			model.put("teacher", teacher);
			model.put("score", score);
			return "scores/createForm";
		}else {
			Score uno = this.scoreService.findScoreById(scoreId);
			BeanUtils.copyProperties(score, uno, "id", "teacher", "student");
			this.scoreService.saveScore(score);
		return "redirect:/teachers/{teacherId}/scores";
		}
	}

	
}
