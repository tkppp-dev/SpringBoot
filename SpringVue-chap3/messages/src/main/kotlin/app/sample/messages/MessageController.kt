package app.sample.messages

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/messages")
class MessageController(private val messageService: MessageService) {
    @GetMapping
    fun saveMessage(): String{
        messageService.save("test")
        return "DB Insert Completed!"
    }
}