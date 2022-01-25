package com.ex.aws_springboot.web.dto

data class PostUpdateRequestDto(
    val title: String = "",
    val content: String = "",
)