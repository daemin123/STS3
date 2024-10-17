package com.example.app.domain.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {
	private Long id;
	private String title;
	private String contents ;
	private String writer;
	private LocalDateTime regDate;
}
