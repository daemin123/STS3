package com.example.app.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SecurityTestController {

	@GetMapping("/user")
	public void user(Authentication authentication,Model model) {
		log.info("GET /user.. authentication : " + authentication);
		log.info("name : " + authentication.getName() );
		log.info("principal : " + authentication.getPrincipal() );
		log.info("authorities :" + authentication.getAuthorities());
		log.info("details : " + authentication.getDetails());
		log.info("credential : "  + authentication.getCredentials());
		
		//Model 로전달
		model.addAttribute("authentication",authentication);
	
		model.addAttribute("name",authentication.getName());
		model.addAttribute("pricipal",authentication.getPrincipal());
		model.addAttribute("isAuthenticated",authentication.isAuthenticated());
		
	}
	@GetMapping("/member")
	public void member(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("authentication",authentication); 
		
		log.info("GET /member..");
	}
	@GetMapping("/admin")
	public void admin() {
		log.info("GET /admin..");
	}
}
