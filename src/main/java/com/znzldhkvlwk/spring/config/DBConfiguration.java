package com.znzldhkvlwk.spring.config;

import java.beans.PropertyVetoException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;

//@Configuration
public class DBConfiguration {
	
	@Bean
	public ComboPooledDataSource dataSource2() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		
		dataSource.setDriverClass("oracle.jdbc.driver.OracleDriver");
		dataSource.setJdbcUrl("jdbc:oracle:thin:@localhost:32118/xepdb1");
		dataSource.setUser("ZNZLDHKVLWK");
		dataSource.setPassword("cookie0318");
		dataSource.setMaxPoolSize(200);
		dataSource.setCheckoutTimeout(60000);
		dataSource.setMaxIdleTime(1800);
		dataSource.setIdleConnectionTestPeriod(600);
		
		return dataSource;
	}
}
