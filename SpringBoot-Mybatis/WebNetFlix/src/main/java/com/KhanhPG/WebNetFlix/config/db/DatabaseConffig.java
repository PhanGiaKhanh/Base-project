package com.KhanhPG.WebNetFlix.config.db;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatabaseConffig {
	@Value("${db.datasource.driver-class-name}")
	private String driverClassName;
	@Value("${db.datasource.url}")
	private String urlDB;
	@Value("${db.datasource.username}")
	private String userName;
	@Value("${db.datasource.password}")
	private String password;

	// Connect database
	@Bean(name = "dataSource")
	public DataSource dataSource() {
		// BasicDataSource dễ dùng, phải import thư viện BasicDataSource(Apache Common
		// DBCP)

		// Hikari mới, nhiều chức năng, có sẵn trong spring boot
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(driverClassName);
		hikariConfig.setJdbcUrl(urlDB);
		hikariConfig.setUsername(userName);
		hikariConfig.setPassword(password);
		return new HikariDataSource(hikariConfig);
	}

	// Quản lý transaction
	@Bean(name = "transactionManager")
	public DataSourceTransactionManager dataSourceTransactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	// Kết nối với .../mapper/sql/*.xml
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);

		/* Set the mapper file location */
		sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
				.getResources("classpath:com/KhanhPG/WebNetFlix/mapper/sql/*.xml"));

		return sessionFactory.getObject();
	}

}
