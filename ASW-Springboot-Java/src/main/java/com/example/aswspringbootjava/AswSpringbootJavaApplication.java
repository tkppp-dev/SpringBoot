package com.example.aswspringbootjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AswSpringbootJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AswSpringbootJavaApplication.class, args);
    }

}
