package com.ctfs.api.config;



import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@Configuration
@ComponentScan({
        "com.ctfs.api",
        "com.ctfs.common.utils",
        "com.ctfs.common.service",
        "com.springbootjdbc.com.spring.jdbc.*"
})
@PropertySource("classpath:/application.properties")
public class ContextConfiguration {
	@Bean
	public JdbcTemplate jdbcTemplate() {         
		return new JdbcTemplate(dataSource());     
		}
	public DataSource dataSource() {         
		DriverManagerDataSource dataSource = new DriverManagerDataSource();         
		//dataSource.setDriverClassName("your.driver.ClassName");         
		dataSource.setUrl("jdbc:oracle:thin:@ctat7dbse1.ctal.ctc:3001:TEST01D");         
		dataSource.setUsername("TEST_OWNER");         
		dataSource.setPassword("TEST01D_xyz_0215");         
		return dataSource;     
		}
}
