package com.ex.aws_springboot.web

import com.ex.aws_springboot.web.domain.posts.TestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {
    @Autowired
    private lateinit var ts: TestService

    @GetMapping("/hello")
    fun hello(): String{
        return "hello"
    }
}