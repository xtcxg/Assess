package com.miex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class AssessApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssessApplication.class);
    }
}
