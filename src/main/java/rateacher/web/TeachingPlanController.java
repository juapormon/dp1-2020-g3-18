package rateacher.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import rateacher.model.Score;
import rateacher.model.Student;
import rateacher.model.Subject;
import rateacher.model.Teacher;
import rateacher.model.TeachingPlan;
import rateacher.service.DeanService;
import rateacher.service.StudentService;
import rateacher.service.SubjectService;
import rateacher.service.TeacherService;
import rateacher.service.TeachingPlanService;

@Controller
public class TeachingPlanController {
	
private static final String VIEW_TEACHINGPLAN_CREATE_FORM ="teachingPlans/newTeachingPlan";
	
	private final SubjectService subjectService;
	private final TeachingPlanService teachingPlanService;
	
	@Autowired
	public TeachingPlanController(SubjectService subjectService, TeachingPlanService teachingPlanService) {
		this.subjectService = subjectService;
		this.teachingPlanService = teachingPlanService;

	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = {"/subjects/{subjectId}/newTeachingPlan"})
	public String newSubject(@PathVariable int subjectId,ModelMap model) {
		
		Subject subject = subjectService.findSubjectById(subjectId);
		model.put("subject", subject);
		TeachingPlan teachingPlan = new TeachingPlan();
		model.put("teachingPlans", teachingPlan);
		return VIEW_TEACHINGPLAN_CREATE_FORM;
	}
	@PostMapping(value = "subjects/{subjectId}/newTeachingPlan")
	public String processCreationForm(@PathVariable int subjectId, @Valid TeachingPlan teachingPlan, BindingResult result,
			ModelMap model) {
		if (result.hasErrors()) {
			model.put("teachingPlans", teachingPlan);
			Subject subject = subjectService.findSubjectById(subjectId);
			model.put("subject", subject);
			return "teachingPlans/newTeachingPlan";
		} else {
			
			Subject subject = subjectService.findSubjectById(subjectId);
			model.put("subject", subject);
			subject.setTeachingPlan(teachingPlan);
			this.subjectService.save(subject);
			return "subjects/subjectsList";
		}
	}
	
}
