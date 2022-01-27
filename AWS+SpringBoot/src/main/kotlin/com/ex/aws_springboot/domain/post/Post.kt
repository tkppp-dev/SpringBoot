package com.ex.aws_springboot.domain.post

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(length = 500, nullable = false)
    var title: String,

    @Column(columnDefinition = "TEXT", nullable = false)
    var content: String,

    val author: String? = null,

    val createdAt: LocalDateTime = LocalDateTime.now(),

    var modifiedAt: LocalDateTime = LocalDateTime.now()
)