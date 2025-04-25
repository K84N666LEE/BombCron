package com.src.job;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JobSchedulerService {

    @Autowired
    private Scheduler scheduler;

    public void scheduleJob(String jobId, String jobGroup) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(DatabaseJob.class)
                .withIdentity(jobId, jobGroup)
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(jobId + "Trigger", jobGroup)
                .startAt(new Date())
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withMisfireHandlingInstructionFireNow())
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
    }
}