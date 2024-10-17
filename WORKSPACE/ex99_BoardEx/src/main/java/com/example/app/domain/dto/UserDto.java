package com.example.app.domain.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class UserDto {
	@NotBlank(message="username을 입력하세요")
	private String username;
	@NotBlank(message="password을 입력하세요")
	private String password;
	@NotBlank(message="password을 다시 입력하세요")
	private String repassword;
	@NotBlank(message="nickname을 입력하세요")
	private String nickname;
	@NotBlank(message="phone을 입력하세요")
	private String phone;
	@NotBlank(message="address을 입력하세요")
	private String address;
	
	//@DateTimeFormat(pattern="yyyy-MM-dd")
	@NotNull(message="birthday를 입력하세요")
	private LocalDate birthday;
	private String hobbys[];
	private String hobbys2[];
	
}
