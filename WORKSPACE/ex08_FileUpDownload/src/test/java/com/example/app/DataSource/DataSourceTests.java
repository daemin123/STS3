package com.example.app.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Slf4j
public class DataSourceTests {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private DataSource dataSource2;
	
	@Autowired
	private DataSource dataSource3;	
	@Test
	public void test() throws SQLException {
		//assertNotNull(dataSource.getConnection());
		log.info(dataSource.toString());
		log.info(dataSource2.toString());
		log.info(dataSource3.toString());
		
		Connection conn = dataSource3.getConnection();
		PreparedStatement pstmt = 
		conn.prepareStatement("insert into user values('abc','1234','ROLE_ABC',0)");		
		pstmt.executeUpdate();
		
	}
	
	

}
