package com.alberto.rallyslot.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfig {

	@Bean
	public Job rallyslotBatchJob(@Autowired JobBuilderFactory jobBuilderFactory,
			@Autowired Step inscritosSinTramosStep, @Autowired Step inicializarTramosStep) {
		return jobBuilderFactory.get("rallyslotBatchJob").incrementer(new SampleIncrementer())
				.start(inscritosSinTramosStep).next(inicializarTramosStep).build();
	}

}