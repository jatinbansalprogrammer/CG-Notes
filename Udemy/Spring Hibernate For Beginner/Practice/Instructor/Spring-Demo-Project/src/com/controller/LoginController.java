package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	@RequestMapping("/helloForm")
	public String getHelloForm() {
		return "helloForm";
	}
	
	@RequestMapping("/processForm")
	public String getProcessForm() {
		return "processForm";
	}
	
}
