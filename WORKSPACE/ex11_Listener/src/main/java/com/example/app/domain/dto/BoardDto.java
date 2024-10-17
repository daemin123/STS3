package com.example.app.domain.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardDto {
	private Long id;
	private String title;
	private String contents;
	private String writer;
	private String regdate;
	private MultipartFile[] files;
	

}
