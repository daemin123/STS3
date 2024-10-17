package com.example.app.Di;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.app.domain.dto.PersonComponent;
import com.example.app.domain.dto.PersonDto;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Slf4j
public class DiTests {

	@Autowired
	private PersonDto personDtoByXml;
	@Autowired
	private PersonDto personDtoByXml2;
	@Autowired
	private PersonDto person03;
	@Autowired
	private PersonDto personBean;
	
	@Autowired
	private PersonComponent personComponent;
	
	@Test
	public void beanTests() {
		log.info(personDtoByXml.toString());
		log.info(personDtoByXml2.toString());
		log.info(person03.toString());
		log.info(personBean.toString());
		log.info(personComponent.toString());
	}
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Test
	public void beanTests_2() {
		log.info(applicationContext.toString());
		log.info(applicationContext.getBean("personDtoByXml").toString());
		log.info(applicationContext.getBean("personDtoByXml2").toString());
		log.info(applicationContext.getBean("person03").toString());
		log.info(applicationContext.getBean("personBean").toString());

		;
		
	}

}
