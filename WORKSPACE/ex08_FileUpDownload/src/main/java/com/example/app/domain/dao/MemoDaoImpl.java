package com.example.app.domain.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.app.domain.dto.MemoDto;


@Repository
public class MemoDaoImpl {
	
	@Autowired
	private SqlSession sqlSession;
	
	private static String namespace="com.example.app.domain.mapper.MemoMapper.";
	
	public MemoDto Insert(MemoDto memoDto) {
		 sqlSession.insert(namespace+"Insert",memoDto);
		 return memoDto;
	}
	
}
