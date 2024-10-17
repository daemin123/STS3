package com.example.app.Mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.app.domain.dto.BoardDto;
import com.example.app.domain.mapper.BoardMapper;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Slf4j
public class MybatisTests {
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void test() {
		//System.out.println(sqlSessionFactory);
		//System.out.println(sqlSession);
		//System.out.println(boardMapper);
		boardMapper.Update(new BoardDto(1L,"제목1234","내용1","홍길동",null));
		
	}
	

}
