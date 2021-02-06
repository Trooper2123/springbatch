package com.trooper2123.springbatchstudy;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbatchStudyApplication implements ApplicationRunner {

    private Userinsert userinsert;

    public static void main(String[] args) {
        SpringApplication.run(SpringbatchStudyApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userinsert.initOddOrEven();
    }
}
