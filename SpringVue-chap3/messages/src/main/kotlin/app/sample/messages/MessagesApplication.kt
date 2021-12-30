package app.sample.messages

import org.apache.logging.log4j.LogManager
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletComponentScan

@ServletComponentScan
@SpringBootApplication
class MessagesApplication
fun main(args: Array<String>) {
    runApplication<MessagesApplication>(*args)
    val logger = LogManager.getLogger(MessagesApplication::class.java)
}
