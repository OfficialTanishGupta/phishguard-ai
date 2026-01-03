package com.phishguard.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class BackendSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendSpringbootApplication.class, args);
    }
}
