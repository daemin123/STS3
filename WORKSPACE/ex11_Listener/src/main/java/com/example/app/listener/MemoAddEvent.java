package com.example.app.listener;

import org.springframework.context.ApplicationEvent;

import com.example.app.domain.dto.MemoDto;

public class MemoAddEvent  extends ApplicationEvent{

	private MemoDto memoDto;
	
	public MemoAddEvent(Object source,MemoDto dto) {
		super(source);
		this.memoDto = dto;
	}

	@Override
	public String toString() {
		return "MemoAddEvent [memoDto=" + memoDto + ", source=" + source + "]";
	}
	
	
	

}
