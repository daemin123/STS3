package com.example.app.domain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.dto.BoardDto;

@Mapper
public interface BoardMapper {
	
	public int Insert(BoardDto memoDto);

	public int Update(BoardDto memoDto);
	
	public int Delete(long id);
	
	public BoardDto SelectOne(long id);
		
	public List<BoardDto> SelectAll();
}
