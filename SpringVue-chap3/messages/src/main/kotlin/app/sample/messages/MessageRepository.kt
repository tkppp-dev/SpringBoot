package app.sample.messages

import org.hibernate.SessionFactory
import org.springframework.stereotype.Repository

@Repository
class MessageRepository(private val sessionFactory: SessionFactory) {

    fun saveMessage(message: Message): Message{
        sessionFactory.openSession().save(message)
        return message
    }
}