package com.ex.aws_springboot.web.dto

import com.ex.aws_springboot.domain.post.Post

data class PostResponseDto(
    val id: Long,
    val title: String,
    val content: String,
    val author: String? = null
){
    constructor(post: Post) : this(
        id = post.id!!,
        title = post.title,
        content = post.content,
        author = post.author
    )
}