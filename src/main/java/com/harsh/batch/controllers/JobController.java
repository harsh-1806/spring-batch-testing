package com.harsh.batch.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.job.parameters.InvalidJobParametersException;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.launch.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.JobRestartException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobOperator jobOperator;

    private final Job job;

    @PostMapping
    public ResponseEntity<String> createJob() {
        final JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis())
                .toJobParameters();



        try {
            final JobExecution jobExecution = jobOperator.start(job, jobParameters);

            return ResponseEntity.ok(jobExecution.getStatus().toString());

        } catch (JobInstanceAlreadyCompleteException | InvalidJobParametersException |
                 JobExecutionAlreadyRunningException | JobRestartException e) {
            throw new RuntimeException(e);
        }
    }
}
