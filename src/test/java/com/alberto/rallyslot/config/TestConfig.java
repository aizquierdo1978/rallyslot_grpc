package com.alberto.rallyslot.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.modelmapper.ModelMapper;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.alberto.rallyslot.controller.club.ClubController;
import com.alberto.rallyslot.dao.club.ClubDao;
import com.alberto.rallyslot.dao.club.impl.ClubDaoImpl;
import com.alberto.rallyslot.service.club.ClubService;
import com.alberto.rallyslot.service.club.impl.ClubServiceImpl;

@TestConfiguration
@MapperScan("com.alberto.rallyslot.mapper.*")
public class TestConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public TestRestTemplate testRestTemplate() {
		return new TestRestTemplate();
	}

	@Bean
	public ClubController clubController() {
		return new ClubController();
	}

	@Bean
	public ClubService clubService() {
		return new ClubServiceImpl();
	}

	@Bean
	public ClubDao clubDao() {
		return new ClubDaoImpl();
	}

	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("schema.sql").build();
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		return factoryBean.getObject();
	}
}
