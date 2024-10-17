# SQLMAPPER_MYBATIS
> <br>

|참고|-|
|-|-|
|SQL MAPPER vs ORM|[바로가기](https://deveun.tistory.com/entry/SQL-Mapper%EC%99%80-ORM-%EC%B0%A8%EC%9D%B4)<br>[바로가기](https://www.elancer.co.kr/blog/view?seq=231)|
|-|-|

SQLMAPPER
---
> 개발자가 작성한 SQL 실행 결과를 객체에 매핑 <br>

> 객체와 테이블간의 관계를 매핑하는 것이 아니라, SQL문을 직접 작성하고 쿼리 수행결과를 어떠한 객체에 매핑하여 줄 지 바인딩하는 방법. 즉 SQL 의존적인 방법 (ex) JdbcTemplate, Mybatis )<br>

> 장점
```
1 Java 코드와 SQL 매핑
 MyBatis를 사용하면, MyBatis 내부에서 그러한 Boilerplate 코드가 구현되어 있고, MyBatis에서 Java 메소드와 SQL 간에 매핑을 시켜서
 개발자는 Java 메소드 선언과 SQL 문만 만들면 MyBatis가 자동으로 그 둘 간을 연결시켜 주게 됩니다.
 SQL 문장이 Java 코드 내에 들어가 있어서 수정하기도 힘들고, 보기도 힘들었는데, SQL 문을 별도로
 Java 코드에서 분리해두어서 관리가 편하게 하였으며, 분리된 SQL 문을 MyBatis가 찾아서 실행해 주는 기능을 합니다.

2 동적 SQL 생성 기능
 MyBatis는 Boilerplate 코드 제거 및 SQL 문 분리 외에도 동적인 SQL(Dynamic SQL) 생성 기능을 제공하여
 프로그램 실행 중에 입력되는 파라미터에 따라 서로 다른 SQL 문을 동적으로 생성해 내는 기능을 제공해 줍니다.
 MyBatis 내에 if 문, choose, when, otherwise, foreach 등의 문법을 지원해서 동적인 SQL 문 생성이 가능합니다
```

> 단점
```
SQL을 개발자가 직접 작성하는 문제.
DBMS에 종속적인 문제.
비슷한 쿼리를 반복적으로 작성해야하는 문제
객체와 관계형 테이블 구조간 패러다임 불일치 발생.
```


#
---

기본 실습
---
> pom.xml <br>

```
		<!-- Mybatis-->
		<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
		<dependency>
		    <groupId>org.mybatis</groupId>
		    <artifactId>mybatis</artifactId>
		    <version>3.5.13</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-spring -->
		<dependency>
		    <groupId>org.mybatis</groupId>
		    <artifactId>mybatis-spring</artifactId>
		    <version>2.0.7</version>
		</dependency>	
```


---
> root-context.xml <br>
 
> > namespaces -> mybatis-spring 체크 <br>

> > 코드입력 <br>
```
	<mybatis-spring:scan base-package="com.example.app.domain.common.mapper" />
```


> MybatisConfig.java <br>

```
@Configuration
public class MybatisConfig {

	@Autowired
	private DataSource dataSource3;

	@Bean 
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource3);
		return sessionFactory.getObject();
	}
	
}

```


> Junit Tests  <br>

```
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MybatisConfigTest_xml {

	@Autowired
	SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void test() {
		 System.out.println("sqlSessionFactory : " + sqlSessionFactory);
		 SqlSession session =  sqlSessionFactory.openSession();
		 System.out.println("SqlSession : " + session);
		 Connection conn = session.getConnection();
		 System.out.println("Connection : " + conn);
	}
}
```


#
---


Annotation SQL MAPPING
---

> com.example.app.domain.common.mapper.MemoMapper <br>

```
@Mapper
public interface MemoMapper {

	@SelectKey(statement="select max(id)+1 from tbl_memo",keyProperty = "id",before = false, resultType = int.class)
	@Insert("insert into tbl_memo values(#{id},#{text})")
	public int insert(MemoDto dto);
	
	
	@Update("update tbl_memo set text=#{text} where id=#{id}")
	public int update(MemoDto dto);
	
	@Delete("delete from tbl_memo where id=#{id}")
	public int delete(int id);
	
	@Select("select * from tbl_memo where id=#{id}")
	public MemoDto selectAt(int id);
	
	
	@Select("select * from tbl_memo")
	public List<MemoDto> selectAll(); 
	
	@Results(id="MemoResultMap", value= {
			@Result(property = "id",column="id"),
			@Result(property = "text", column="text")
	})
	@Select("select * from tbl_memo")
	public List< Map<String,Object> > selectAllResultMap(); 
}
```

> JUnit Tests <br>

```
	@Autowired
	MemoMapper memoMapper;
	
	@Test
	public void test2() {
		System.out.println("memoMapper : " + memoMapper);	
		memoMapper.insert(new MemoDto(10,"HAHAHA"));
	}
	@Test
	public void test3() {
		System.out.println("memoMapper : " + memoMapper);	
		memoMapper.update(new MemoDto(10,"WOW~"));
	}	
	@Test
	public void test4() {
		System.out.println("memoMapper : " + memoMapper);	
		memoMapper.delete(10);
	}	
	@Test
	public void test5() {
		System.out.println("memoMapper : " + memoMapper);	
		MemoDto dto = memoMapper.selectAt(10);
		System.out.println("selectAt dto : " + dto);
	}	
	
	@Test
	public void test6() {
		System.out.println("memoMapper : " + memoMapper);	
		List<MemoDto> list = memoMapper.selectAll();
		list.forEach((dto)->{System.out.println(dto);});		
	}	
	
	@Test
	public void test7() {
		System.out.println("memoMapper : " + memoMapper);	
		List< Map<String,Object> > list = memoMapper.selectAllResultMap();
		
		list.forEach((map)->{
			// System.out.println("map : " + map);
			for(String key  : map.keySet())
			{
				System.out.println(key +" : " + map.get(key));
			}
		});
				
	}		
	@Test
	public void test8() {
		System.out.println("memoMapper : " + memoMapper);	
		MemoDto dto = new MemoDto(16,"AABBCC");
		int cnt = memoMapper.insert(dto);
		System.out.println("증가된 행수 : " + cnt);
		System.out.println("다음 Id 값 : " + dto.getId());
	}	 
```

# 
---

XML SQL MAPPING
---
> MyBatisConfig<br>

> > mapper.xml 경로 추가 <br>

```
@Configuration
public class MybatisConfig {

	@Autowired
	private DataSource dataSource3;

	@Bean 
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource3);
		
		
	    // Mapper XML 파일의 위치 설정
	    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	    Resource[] resources = resolver.getResources("classpath*:mapper/*.xml");
	    sessionFactory.setMapperLocations(resources);
	    
		return sessionFactory.getObject();
	}
	
}
```


> src/main/resources/mapper/MemoMapper.xml <br>

```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.example.app.domain.common.mapper.MemoMapper">

    <insert id="insertXml" useGeneratedKeys="true" keyProperty="id">
        <selectKey keyProperty="id" order="AFTER" resultType="int">
            select max(id) + 1 as id from tbl_memo
        </selectKey>
        insert into tbl_memo (id, text) values (#{id}, #{text})
    </insert>

    <update id="updateXml">
        update tbl_memo set text=#{text} where id=#{id}
    </update>
    
    <delete id="deleteXml">
        delete from tbl_memo where id=#{id}
    </delete>
    
    <select id="selectAtXml" resultType="com.example.app.domain.common.dto.MemoDto" parameterType="int">
        select * from tbl_memo where id=#{id}
    </select>
    
    <select id="selectAllXml" resultType="com.example.app.domain.common.dto.MemoDto">
        select * from tbl_memo
    </select>
    
    <select id="selectAllResultMapXml" resultType="java.util.Map">
        select * from tbl_memo
    </select>

</mapper>

```

> MemoMapper Interface <br>

```
	//-----------------
	//XML
	//-----------------
	public int insertXml(MemoDto dto);
	public int updateXml(MemoDto dto);
	public int deleteXml(@Param("id") int id);
	public MemoDto selectAtXml(int id);
	public List<MemoDto> selectAllXml(); 
	public List< Map<String,Object> > selectAllResultMapXml();
	
```

> JUNIT Tests <br>

```
package ex06_ORM_Mybatis;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.app.domain.common.dto.MemoDto;
import com.example.app.domain.common.mapper.MemoMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MybatisConfigTest_xml {

	@Autowired
	SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void test() {
		 System.out.println("sqlSessionFactory : " + sqlSessionFactory);
		 SqlSession session =  sqlSessionFactory.openSession();
		 System.out.println("SqlSession : " + session);
		 Connection conn = session.getConnection();
		 System.out.println("Connection : " + conn);
	}
	
	
	@Autowired
	MemoMapper memoMapper;
	
	@Test
	public void test2() {
		System.out.println("memoMapper : " + memoMapper);
		memoMapper.insertXml(new MemoDto(10,"HAHAHA"));
	}
	
	@Test
	public void test3() {
		System.out.println("memoMapper : " + memoMapper);	
		memoMapper.updateXml(new MemoDto(10,"WOW~"));
	}	
	
	@Test
	public void test4() {
		System.out.println("memoMapper : " + memoMapper);	
		memoMapper.deleteXml(10);
	}	
	
	@Test
	public void test5() {
		System.out.println("memoMapper : " + memoMapper);	
		MemoDto dto = memoMapper.selectAtXml(10);
		System.out.println("selectAt dto : " + dto);
	}	
	
	@Test
	public void test6() {
		System.out.println("memoMapper : " + memoMapper);	
		List<MemoDto> list = memoMapper.selectAllXml();
		list.forEach((dto)->{System.out.println(dto);});		
	}	
	
	@Test
	public void test7() {
		System.out.println("memoMapper : " + memoMapper);	
		List< Map<String,Object> > list = memoMapper.selectAllResultMapXml();
		
		list.forEach((map)->{
			//			System.out.println("map : " + map);
			for(String key  : map.keySet())
			{
				System.out.println(key +" : " + map.get(key));
			}
		});
				
	}		
	@Test
	public void test8() {
		System.out.println("memoMapper : " + memoMapper);	
		MemoDto dto = new MemoDto(16,"AABBCC");
		int cnt = memoMapper.insert(dto);
		System.out.println("증가된 행수 : " + cnt);
		System.out.println("다음 Id 값 : " + dto.getId());
	}	
	
	
}
```

#
---

Dao에 Mapper 사용하기
---
> MybatisConfig.java 에 추가<br>

```
@Configuration
public class MybatisConfig {

	@Autowired
	private DataSource dataSource3;

	@Bean 
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource3);
		
		
	    // Mapper XML 파일의 위치 설정
	    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	    Resource[] resources = resolver.getResources("classpath*:mapper/*.xml");
	    sessionFactory.setMapperLocations(resources);
	    
		return sessionFactory.getObject();
	}
	
	//-----------------------
	// sqlSession 생성
	//-----------------------
	    @Autowired
	    private SqlSessionFactory sqlSessionFactory;
	
	    @Bean
	    public SqlSessionTemplate sqlSessionTemplate() {
	        return new SqlSessionTemplate(sqlSessionFactory);
	    }
}
```

> MemoDaoImpl 생성 <br>

```
	@Repository
	public class MemoDaoImpl {
		
		@Autowired
		private SqlSession session;
		
		private static String namespace="com.example.app.domain.common.mapper.MemoMapper.";
		
		
		public MemoDto memoRegistration(MemoDto memoDto) {
			session.insert(namespace + "insertXml",memoDto);
			return memoDto;
		}
		
	}

```

> JUNIT Tests <br>

```
	@Autowired
	MemoDaoImpl memoDaoImpl;
	
	@Test
	public void test9() {
		System.out.println("memoDaoImpl : " + memoDaoImpl);
		memoDaoImpl.memoRegistration(new MemoDto(15,"HAHAHA"));
	}
```



#
---
동적 SQL
---
> -  <br>

|참고|
|-|
|[바로가기](https://mybatis.org/mybatis-3/ko/dynamic-sql.html)|


> mapper.xml <br>

```
	//동적쿼리
	@Test
	public void selectIfTests() {
		Map<String,Object>map = new HashMap();
		map.put("type", "text");
		map.put("text", "H");
		
		List< Map<String,Object>> result =	memoMapper.selectIf(map);
		
		for(Map<String,Object> tmp : result) {
			for(String key : tmp.keySet()) {
				 System.out.println("KEY : " + key +" VAL : " + tmp.get(key));
			}
		}
	}
	@Test
	public void selectWhenTests() {
		Map<String,Object>map = new HashMap();
		map.put("condition", true);
		map.put("type", "id");
		map.put("id", 2);
		
		List< Map<String,Object>> result =	memoMapper.selectWhen(map);
		
		for(Map<String,Object> tmp : result) {
			for(String key : tmp.keySet()) {
				 System.out.println("KEY : " + key +" VAL : " + tmp.get(key));
			}
		}
	}
```

> MemoMapper Interface <br>

```
	//동적 쿼리
	public List< Map<String,Object> >  selectIf( Map<String,Object> map );	
	public List< Map<String,Object> >  selectWhen( Map<String,Object> map );
```

> JUNIT Tests<br>

```
	//동적쿼리
	@Test
	public void selectIfTests() {
		Map<String,Object>map = new HashMap();
		map.put("type", "text");
		map.put("text", "H");
		
		List< Map<String,Object>> result =	memoMapper.selectIf(map);
		
		for(Map<String,Object> tmp : result) {
			for(String key : tmp.keySet()) {
				 System.out.println("KEY : " + key +" VAL : " + tmp.get(key));
			}
		}
	}
	@Test
	public void selectWhenTests() {
		Map<String,Object>map = new HashMap();
		map.put("condition", true);
		map.put("type", "id");
		map.put("id", 2);
		
		List< Map<String,Object>> result =	memoMapper.selectWhen(map);
		
		for(Map<String,Object> tmp : result) {
			for(String key : tmp.keySet()) {
				 System.out.println("KEY : " + key +" VAL : " + tmp.get(key));
			}
		}
	}
```


