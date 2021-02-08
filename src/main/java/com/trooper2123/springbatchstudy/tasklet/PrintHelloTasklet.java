package com.trooper2123.springbatchstudy.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PrintHelloTasklet implements Tasklet {

    @Value("${job_parameter_name}")
    private String name;


    @Override
    public RepeatStatus execute( StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println(String.format("Hello,%S",name));
        return RepeatStatus.FINISHED;
    }
}
