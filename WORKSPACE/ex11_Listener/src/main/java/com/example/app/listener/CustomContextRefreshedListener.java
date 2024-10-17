package com.example.app.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.context.support.RequestHandledEvent;

public class CustomContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent>{

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println("[CustomContextRefreshedListener] EVENT : " + event.getSource());
		
	}

	

}
