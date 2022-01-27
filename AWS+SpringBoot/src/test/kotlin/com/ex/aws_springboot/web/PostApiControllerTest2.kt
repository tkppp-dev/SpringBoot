package com.ex.aws_springboot.web

import com.ex.aws_springboot.domain.post.Post
import com.ex.aws_springboot.domain.post.PostRepository
import com.ex.aws_springboot.web.dto.PostResponseDto
import com.ex.aws_springboot.web.dto.PostSaveRequestDto
import com.ex.aws_springboot.web.dto.PostUpdateRequestDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.*
import java.time.LocalDateTime

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostApiControllerTest2(
    @Autowired private val postRepository: PostRepository,
    @Autowired private val restTemplate: TestRestTemplate,
    @LocalServerPort private val port: Int
) {
    val title = "title"
    val content = "content"

    @BeforeEach
    fun tearDown() {
        postRepository.deleteAll()
    }

    @Test
    fun savePost_shouldSuccess() {
        // given
        val requestDto = PostSaveRequestDto(
            title = title,
            content = content
        )
        val url = "http://localhost:${port}/api/v1/post"

        // when
        val responseEntity: ResponseEntity<Long?> = restTemplate
            .postForEntity(url, requestDto, Long::class.java)
        // then
        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(responseEntity.headers.contentType).isEqualTo(MediaType.APPLICATION_JSON)

        val post = postRepository.findAll().first()
        assertThat(post.title).isEqualTo(title)
        assertThat(post.content).isEqualTo(content)
    }

    @Test
    fun getPost_shouldSuccess(){
        val post: Post = postRepository.save(
            Post(
                title = title,
                content = content
            )
        )

        val postId = post.id
        val url = "http://localhost:${port}/api/v1/post/$postId"

        // when
        val responseEntity: ResponseEntity<PostResponseDto> =
            restTemplate.getForEntity(url, PostResponseDto::class.java)

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(responseEntity.headers.contentType).isEqualTo(MediaType.APPLICATION_JSON)
        assertThat(responseEntity.body?.title).isEqualTo(title)
        assertThat(responseEntity.body?.content).isEqualTo(content)
        assertThat(responseEntity.body?.author).isNull()
    }

    @Test
    fun updatePost_shouldSuccess() {
        // given
        var post: Post = postRepository.save(
            Post(
                title = title,
                content = content
            )
        )
        val baseTime = LocalDateTime.now()
        val updateId = post.id
        val url = "http://localhost:${port}/api/v1/post/$updateId"
        val updatedTitle = "updated title"
        val updatedContent = "updated content"

        val requestDto = PostUpdateRequestDto(title = updatedTitle, content = updatedContent)

        // when
        val responseEntity: ResponseEntity<Long?> =
            restTemplate.exchange(url, HttpMethod.PUT, HttpEntity(requestDto), Long::class.java)

        // then
        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(responseEntity.body).isGreaterThan(0L)

        post = postRepository.findAll().first()
        assertThat(post.title).isEqualTo(updatedTitle)
        assertThat(post.content).isEqualTo(updatedContent)
        assertThat(post.createdAt).isBefore(baseTime)
        assertThat(post.modifiedAt).isAfter(baseTime)
    }
}