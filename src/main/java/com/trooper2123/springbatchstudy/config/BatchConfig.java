package com.trooper2123.springbatchstudy.config;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.batch.api.chunk.ItemReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    public Job printOddOrEvenJob() {
        return jobBuilderFactory
                .get("printOddOrEvenJob")
                .start(printOddOrEvenStep())
                .incrementer(new RunIdIncrementer())
                .build();
    }

    public Step printOddOrEvenStep() {
        return stepBuilderFactory
                .get("printOddOrEvenStep")
                .<Integer, String>chunk(1)
                .reader(countUntillTenReader())
                .processor(oddOrEvenProcessor())
                .writer(printWriter())
                .build();
    }

    public IteratorItemReader<Integer> countUntillTenReader(){
        List<Integer> numberUntilTen = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        return new IteratorItemReader<Integer>(numberUntilTen.iterator());
    }

    public FunctionItemProcessor<Integer,String> oddOrEvenProcessor(){
        return new FunctionItemProcessor<Integer,String>(
                item -> item % 2 ==0? String.format("Item %s is Even",item): String.format("Item %s is Odd",item)
        );
    }

    public ItemWriter<String> printWriter(){
        return itens -> itens.forEach(System.out::println);
    }
}
