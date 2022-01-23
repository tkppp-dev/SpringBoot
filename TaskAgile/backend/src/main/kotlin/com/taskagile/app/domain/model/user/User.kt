package com.taskagile.app.domain.model.user

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user")
class User(
    @Id
    @GeneratedValue
    var id: Long? = null,

    @Column(name = "username", length = 50, unique = true)
    var username: String = "",

    @Column(name = "email_address", length = 100, unique = true)
    var emailAddress: String = "",

    @Column(name = "password", length = 30)
    var password: String = "",

    @Column(name = "first_name", length = 45)
    var firstName: String = "",

    @Column(name = "last_name", length = 45)
    var lastName: String = "",

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    var createdDate: Date = Date()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (!(other is User)) return false
        val user: User = other
        return user.username == this.username && user.emailAddress == this.emailAddress
    }

    override fun hashCode(): Int {
        return Objects.hash(username, emailAddress)
    }

    override fun toString() =
        "User(id = ${id}, emailAddress = ${emailAddress}. password = <Protected>, firstName = ${firstName}, lastName = ${lastName}, createdDate = ${createdDate}"
}