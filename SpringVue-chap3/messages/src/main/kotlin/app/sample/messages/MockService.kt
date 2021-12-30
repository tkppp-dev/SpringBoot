package app.sample.messages

import org.springframework.stereotype.Service

@Service
class MockService {
    fun printName() = println("I'm MockService")
}