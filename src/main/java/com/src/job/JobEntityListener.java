package com.src.job;

import jakarta.persistence.PostPersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobEntityListener {

    @Autowired
    private JobSchedulerService jobSchedulerService;

    @PostPersist
    public void onPostPersist(JobEntity jobEntity) {

        try {
            jobSchedulerService.scheduleJob(jobEntity.getId().toString(), "JobGroup");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}