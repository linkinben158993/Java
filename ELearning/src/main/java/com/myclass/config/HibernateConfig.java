package com.myclass.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.myclass.constants.StringConstants;

@Configuration
@EnableTransactionManagement()
public class HibernateConfig {
	@Bean
	public DataSource dataSource() {
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName(StringConstants.DATA_SOURCE_DRIVER_CLASS_NAME);
		
		dataSource.setUrl(StringConstants.DATA_SOURCE_URL);
		
		dataSource.setUsername(StringConstants.DATA_SOURCE_USER_NAME);
		
		dataSource.setPassword(StringConstants.DATA_SOURCE_PASSWORD);
		
		return dataSource;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
		
		bean.setDataSource(dataSource());
		
		bean.setPackagesToScan(StringConstants.SESSION_FACTORY_PACKAGE_TO_SCAN);
		
		Properties properties = new Properties();
		
		properties.put(StringConstants.SESSION_FACTORY_PUT_DIALECT, 
				StringConstants.SESSION_FACTORY_PUT_DIALECT_DATABASE);
		
		properties.put(StringConstants.SESSION_FACTORY_PUT_SHOW, true);

		properties.put(StringConstants.SESSION_FACTORY_PUT_FORMAT, true);
		
		bean.setHibernateProperties(properties);

		return bean;
	}
	
	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager manager = new HibernateTransactionManager();
		manager.setSessionFactory(sessionFactory().getObject());
		return manager;
	}
}
