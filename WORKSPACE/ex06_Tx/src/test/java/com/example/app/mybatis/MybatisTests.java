package com.example.app.mybatis;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.app.domain.dto.MemoDto;
import com.example.app.domain.mapper.MemoMapper;

import lombok.extern.slf4j.Slf4j;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Slf4j
public class MybatisTests {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void sqlSessionFactoryTests() {
		log.info(sqlSessionFactory.toString());
		SqlSession session =  sqlSessionFactory.openSession();
		Connection conn = session.getConnection();
		log.info(conn.toString());
	}
	
	@Autowired
	private MemoMapper memoMapper;
	
	@Test
	public void MemoMapperTests() {	
		//memoMapper.Insert(new MemoDto(123,"TEST"));
		//memoMapper.Update(new MemoDto(123,"ABCCE"));
		//memoMapper.Delete(123);
		
//		for(int i=1;i<=10;i++) {
//			memoMapper.Insert(new MemoDto(i,"TEST"+i));	
//		}
		
//		MemoDto r1 = memoMapper.SelectAt(3);
//		log.info(r1.toString());
		
//		List<MemoDto> list =  memoMapper.SelectAll();
//		for(MemoDto dto : list)
//			log.info(dto.toString());
		
		
		List< Map<String,Object> > result =  memoMapper.SelectAllResultMap();
		result.forEach((map)->{
			for(String key : map.keySet()) {
				log.info(key+" : " + map.get(key));
			}		
		});
	}
	
	
	@Test
	public void MybatisTestsXml() {
		//memoMapper.InsertXML(new MemoDto(1515,"TEST"));
		//memoMapper.UpdateXML(new MemoDto(1515,"MODIFIED.."));
		//memoMapper.DeleteXML(1515);
		
//		MemoDto dto =  memoMapper.SelectAtXML(3);
//		log.info(dto.toString());
		
//		List<MemoDto> list = memoMapper.SelectAllXML();
//		for(MemoDto dto : list)
//			log.info(dto.toString());
		
		
		List<Map<String,Object>> result =  memoMapper.SelectAllResultMapXML();
		result.forEach(map->{
			for(String key : map.keySet()) {
				log.info(key + " | " + map.get(key));
			}
		});
		
	}
	
	
	@Test
	public void SelectKeyTests() {
		//memoMapper.Insert(new MemoDto(-1,"HAHA"));
		
//		MemoDto dto = new MemoDto(-1,"DADA");
//		memoMapper.Insert(dto);
//		log.info(dto.toString());
		
		MemoDto dto = new MemoDto(-1,"WOW2");
		memoMapper.InsertXML(dto);
		log.info(dto.toString());
	}
	
	@Test
	public void DynamicQuerys() {
		//전체 검색
//		Map<String,Object> param = new HashMap();
//		param.put("type", "text");
//		param.put("text", "O");
//		List<Map<String,Object>> result =  memoMapper.SelectIf(param);
//		result.forEach(map->{
//			for(String key : map.keySet()) {
//				System.out.println("KEY : " + key +" VAL : " + map.get(key));
//			}
//		});
		
		
		Map<String,Object> param = new HashMap();
		param.put("condition", true);
		param.put("type", "id");
		param.put("id", "2");
		List<Map<String,Object>> result =  memoMapper.SelectWhen(param);
		result.forEach(map->{
			for(String key : map.keySet()) {
				System.out.println("KEY : " + key +" VAL : " + map.get(key));
			}
		});
	}
	
	
}






