package com.ex.aws_springboot.domain.post

import com.ex.aws_springboot.web.dto.PostResponseDto
import com.ex.aws_springboot.web.dto.PostSaveRequestDto
import com.ex.aws_springboot.web.dto.PostUpdateRequestDto
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class PostService(
    private val postRepository: PostRepository
) {

    @Transactional
    fun save(requestDto: PostSaveRequestDto): Long? = postRepository.save(requestDto.toEntity()).id

    @Transactional
    fun update(id: Long, requestDto: PostUpdateRequestDto): Long? {
        val post = postRepository.findByIdOrNull(id) ?: throw IllegalArgumentException("id = $id, 해당하는 게시글이 없습니다.")

        post.title = requestDto.title
        post.content = requestDto.content

        return id
    }

    @Transactional
    fun findPostById(id: Long): PostResponseDto {
        val post = postRepository.findByIdOrNull(id) ?: throw IllegalArgumentException("id = $id, 해당하는 게시글이 없습니다.")
        return PostResponseDto(post)
    }
}