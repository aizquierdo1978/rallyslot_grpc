package com.alberto.rallyslot.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.modelmapper.ModelMapper;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.batch.core.configuration.BatchConfigurationException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableAutoConfiguration
@MapperScan("com.alberto.rallyslot.mapper.*")
@PropertySource("classpath:grpc.properties")
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class RallyslotConfig {

	@Value("${grpc.core.server.name:localhost}")
	private String server;

	@Value("${grpc.core.server.port:8092}")
	private Integer port;

	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		return factoryBean.getObject();
	}

	@Bean
	public ManagedChannel channel() {
		ManagedChannel channel = ManagedChannelBuilder.forAddress(server, port)
				.usePlaintext()
				.build();
		return channel;
	}

	@Bean(name = "jobRepositoryRallyslot")
	public JobRepository getJobRepository(@Autowired DataSource dataSource,
			@Autowired PlatformTransactionManager platformTransactionManager) {
		JobRepositoryFactoryBean factoryBean = new JobRepositoryFactoryBean();
		factoryBean.setDataSource(dataSource);
		factoryBean.setTransactionManager(platformTransactionManager);
		factoryBean.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");
		try {
			factoryBean.afterPropertiesSet();
			return factoryBean.getObject();
		} catch (Exception e) {
			throw new BatchConfigurationException(e);
		}
	}

	@Bean
	@Scheduled(cron = "0 */1 * * * ?")
	public JobLauncher jobLauncherRallyslot(@Autowired JobRepository jobRepositoryRallyslot) {
		SimpleJobLauncher jobLauncherRallyslot = new SimpleJobLauncher();
		jobLauncherRallyslot.setJobRepository(jobRepositoryRallyslot);
		return jobLauncherRallyslot;
	}

}
