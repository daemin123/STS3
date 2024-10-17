package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.domain.dto.MemoDto;

import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/memo")
@Slf4j
public class MemoController {
	
	@GetMapping("/add")
	public void memo_get() {
		log.info("GET /memo/add..");
	}
	
	@PostMapping(value = "/add")
	public void moemo_post(@ModelAttribute MemoDto memoDto) {
		log.info("POST /memo/add..memoDto : " + memoDto);	
		
	}
	
}
