package com.example.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.app.domain.dto.PersonDto;

@Configuration
public class PersonDtoBeanConfig {

	@Bean
	public PersonDto person03() {
		return PersonDto.builder()
				.name("김범수")
				.addr("서울")
				.age(49)
				.build();
	}
	@Bean(name = "personBean")
	public PersonDto person04() {
		return PersonDto.builder()
				.name("박효신")
				.addr("대구")
				.age(15)
				.build();
	}
}
