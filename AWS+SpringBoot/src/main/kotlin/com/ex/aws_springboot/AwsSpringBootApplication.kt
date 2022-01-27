package com.ex.aws_springboot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class AwsSpringBootApplication

fun main(args: Array<String>) {
    runApplication<AwsSpringBootApplication>(*args)
}
