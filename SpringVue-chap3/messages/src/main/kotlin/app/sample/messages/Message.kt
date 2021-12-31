package app.sample.messages

import java.sql.Date
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "messages")
class Message(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Int? = null,

    @Column(name = "text", nullable = false, length = 128)
    var text: String,

    @Column(name = "created_date", nullable = false)
    var createdDate: LocalDateTime = LocalDateTime.now()
)

