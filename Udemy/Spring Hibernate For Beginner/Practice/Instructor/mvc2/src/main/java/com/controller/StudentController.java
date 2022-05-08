package com.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.Entity.School;
import com.Entity.Student;

@Controller
public class StudentController {
	
	@Value("#{countryCodes}")
	private Map countries;
	
	
	
	@RequestMapping("/studentForm")
	public String getStudentForm(@RequestParam("name") String name,Model model) {
		
		
		
	}
	
	
	
	@RequestMapping("/processStudentForm")
	public ModelAndView processStudentForm(@ModelAttribute Student student) {
		ModelAndView mav = new ModelAndView("processStudentForm");
		School school = new School();
		school.setSchoolname("Vikash");
		school.setAddress("Unit-1");
		mav.addObject("message", "This is welcome Page");
		return mav;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * @RequestMapping("/processStudentForm/student/form") public String
	 * rough(@ModelAttribute Student student, Model model) { //ModelAndView mav =
	 * new ModelAndView("processStudentForm"); model.addAttribute("alphabatelist",
	 * "Hello World"); return "processStudentForm"; }
	 */
	
	
	
	
	
	
	
	
	
}
