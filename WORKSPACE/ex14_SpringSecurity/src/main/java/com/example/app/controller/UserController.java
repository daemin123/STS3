package com.example.app.controller;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.domain.dto.UserDto;
import com.example.app.domain.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {
	
	@InitBinder
	public void dataBinder(WebDataBinder webDataBinder) {
		log.info("UserController's dataBinder .." + webDataBinder);
		//birthDay
		webDataBinder.registerCustomEditor(LocalDate.class, "birthday", new UserDtoEditor());
		//hobbys2
		webDataBinder.registerCustomEditor(String[].class,new StringArrayPropertyEditor("#"));
	}
	
	@GetMapping("/join")
	public void join() {
		log.info("GET /join...");
	}
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@PostMapping("/join")
	public String join_post(@Valid UserDto userDto,BindingResult bindingResult ,Model model) {
		log.info("POST /join..."+userDto);	
		
		if(bindingResult.hasFieldErrors()) {
			//log.info("ValidationCheck Error : "+bindingResult.getFieldError("id").getDefaultMessage());
			for(FieldError error  :bindingResult.getFieldErrors()) {
				log.info("ErrorField : " + error.getField() + " ErrorMsg : " + error.getDefaultMessage());
				model.addAttribute(error.getField(),error.getDefaultMessage());
			}
		}
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		userMapper.Insert(userDto);
		
		return "redirect:/login";
		
	}	
	
	
	@GetMapping("/login")
	public void login(Exception exception) {
		log.info("GET /login .." + exception);
	}
	
	
}


//UserDtoEditor
@Slf4j
class UserDtoEditor extends PropertyEditorSupport{
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		log.info("UserDtoEditor's setAsText : " + text);
		text = text.replaceAll("#", "-");
		
		if(text.equals("")) {
			text=LocalDate.now().toString();
		}
		
		LocalDate date =  LocalDate.parse(text,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		log.info("date : " + date);
		
		setValue(date);
	}
	
	
	
}




