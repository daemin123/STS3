# DATASOURCE

pom.xml
---
> Dependencies <br>

```
		 <!-- DATABASE -->
		 <!-- https://mvnrepository.com/artifact/com.mysql/mysql-connector-j -->
		<dependency>
		    <groupId>com.mysql</groupId>
		    <artifactId>mysql-connector-j</artifactId>
		    <version>8.1.0</version>
		</dependency>
		
		<!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc -->
		<dependency>
		    <groupId>org.springframework</groupId>
		    <artifactId>spring-jdbc</artifactId>
		    <version>5.0.7.RELEASE</version>
		</dependency>
		
		
		<!-- https://mvnrepository.com/artifact/com.zaxxer/HikariCP -->
		<dependency>
		    <groupId>com.zaxxer</groupId>
		    <artifactId>HikariCP</artifactId>
		    <version>5.0.1</version>
		</dependency>
```

DataSourceConfig
---
>DataSource / HikariCP <br>

```
@Configuration
public class DataSourceConfig {

	
	// Spring-jdbc DataSource	
	@Bean
	public DataSource dataSource2()
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/bookdb");
		dataSource.setUsername("root");
		dataSource.setPassword("1234");	
		 
		return dataSource;
	}

	
//	HikariCP DataSource
	@Bean
	public HikariDataSource dataSource3()
	{
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/bookdb");
		dataSource.setUsername("root");
		dataSource.setPassword("1234");	
		 
		return dataSource;
	}
	
}

```

Test
---
>  <br>

```
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Slf4j
public class C01DataSource {

	@Autowired
	private DataSource dataSource;
	
	@Test
	public void xml_bean_dataSource() throws SQLException {
		assertNotNull(dataSource);
		assertNotNull(dataSource.getConnection());	
		log.info(dataSource.toString());
	}
	@Autowired
	private DataSource dataSource2;
	
	@Test
	public void rootConfig_dataSource() throws SQLException {
		assertNotNull(dataSource2);
		assertNotNull(dataSource2.getConnection());	
		log.info(dataSource2.toString());
	}
	@Autowired
	private DataSource dataSource3;
	
	@Test
	public void rootConfig_dataSource_Hikari() throws SQLException {
		assertNotNull(dataSource3);
		assertNotNull(dataSource3.getConnection());
		log.info(dataSource3.toString());
	}
	
}
```

