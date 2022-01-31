package com.ex.aws_springboot.domain.post

import com.ex.aws_springboot.domain.BaseTimeEntity
import com.ex.aws_springboot.web.dto.PostUpdateRequestDto
import javax.persistence.*

@Entity
class Post(
    title : String,
    content: String
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(length = 500, nullable = false)
    var title: String = title
        protected set

    @Column(columnDefinition = "TEXT", nullable = false)
    var content: String = content
        protected set

    val author: String? = null


    fun update(updateDto: PostUpdateRequestDto){
        title = updateDto.title
        content = updateDto.content
    }

    @PrePersist
    fun testPrePersist(){
        println("PrePersist Activate")
    }
}