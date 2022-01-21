package com.taskagile.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class TaskAgileApplication

fun main(args: Array<String>) {
    runApplication<TaskAgileApplication>(*args)
}
