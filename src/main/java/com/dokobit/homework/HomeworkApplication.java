package com.dokobit.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class HomeworkApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(HomeworkApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
