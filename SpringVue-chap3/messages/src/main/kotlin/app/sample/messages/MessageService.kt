package app.sample.messages

import org.springframework.stereotype.Component

@Component
class MessageService(private val repository: MessageRepository) {
    fun save(text: String) = repository.saveMessage(Message(text = text))
}