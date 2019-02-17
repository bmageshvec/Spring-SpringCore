package com.pluralsight;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@ComponentScan({"com.pluralsight"})
@PropertySource("classpath:ridetracker.properties")
/*
 * @PropertySources({
 * 
 * @PropertySource("classpath:ridetracker.properties"),
 * 
 * @PropertySource("classpath:ridetracker.properties") })
 */
public class RideTrackerConfig {
	
	@Autowired
	Environment env;
	
	@Bean
	public DataSource getDataSource()
	{
		System.out.println("====================>"+env.getProperty("jdbc.url"));
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/ride_tracker");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}
	
	@Bean
	public JdbcTemplate getJdbcTemplate()
	{
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate;
	}
	
	@Bean
	public DataSourceTransactionManager getTransactionManager()
	{
		return new DataSourceTransactionManager(getDataSource());
	}

}
