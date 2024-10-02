package com.sbi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/home")
	public String  home() {
		return "home";
	}	
	
	@GetMapping("/admin/home")
	public String  adminPage() {
		return "admin_home";
	}	
	@GetMapping("/user/home")
	public String  userPage() {
		return "user_home";
	}

}
