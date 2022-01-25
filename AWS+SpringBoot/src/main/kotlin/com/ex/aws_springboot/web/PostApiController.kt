package com.ex.aws_springboot.web

import com.ex.aws_springboot.domain.post.PostService
import com.ex.aws_springboot.web.dto.PostResponseDto
import com.ex.aws_springboot.web.dto.PostSaveRequestDto
import com.ex.aws_springboot.web.dto.PostUpdateRequestDto
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.google.gson.Gson
import org.springframework.web.bind.annotation.*

@RestController
class PostApiController(
    private val postService: PostService
) {

    @GetMapping("/api/v1/post/{id}")
    fun findPostById(@PathVariable id: Long): PostResponseDto = postService.findPostById(id)

    @PostMapping("/api/v1/post")
    fun save(@RequestBody requestDto: PostSaveRequestDto): Long? = postService.save(requestDto)

    @PutMapping("/api/v1/post/{id}")
    fun update(@PathVariable id: Long, @RequestBody requestDto: PostUpdateRequestDto): Long? =
        postService.update(id, requestDto)
}