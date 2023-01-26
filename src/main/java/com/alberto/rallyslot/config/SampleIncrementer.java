package com.alberto.rallyslot.config;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;

public class SampleIncrementer implements JobParametersIncrementer {

	@Override
	public JobParameters getNext(JobParameters parameters) {
		return new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
	}

}
