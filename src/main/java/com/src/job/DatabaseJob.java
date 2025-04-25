package com.src.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class DatabaseJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String jobName = context.getJobDetail().getKey().getName();
        System.out.println("Executing job: " + jobName);
        // Job 실행 로직 추가
    }
}
