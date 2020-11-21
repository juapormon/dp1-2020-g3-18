package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Dean;
import org.springframework.samples.petclinic.model.Teacher;
import org.springframework.samples.petclinic.service.DeanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/deans/{deanId}")
public class DeanController {
	
	private final DeanService deanService;
	
	
	@Autowired
	public DeanController(DeanService deanService) {
		this.deanService = deanService;
	}
	
	@InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }


	@GetMapping(value = {"/teachers/new"})
	public String newTeacherCreationForm(ModelMap model) {

		Teacher teacher = new Teacher();
		model.put("teachers", teacher);
		return "teachers/newTeacherCreationForm";

	}


}
