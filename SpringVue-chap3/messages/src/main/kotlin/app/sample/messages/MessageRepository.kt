package app.sample.messages

import org.apache.logging.log4j.LogManager
import org.hibernate.SessionFactory
import org.springframework.stereotype.Repository

@Repository
class MessageRepository(private val sessionFactory: SessionFactory) {

    fun saveMessage(message: Message): Message{
        sessionFactory.openSession().save(message)
        return message
    }
}