package com.ex.aws_springboot.web

import com.ex.aws_springboot.domain.post.PostRepository
import com.ex.aws_springboot.web.dto.PostSaveRequestDto
import com.google.gson.Gson
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class PostApiControllerTests(
    @Autowired private val mvc: MockMvc,
    @Autowired private val postRepository: PostRepository,
    @Autowired private val gson: Gson
) {

    @AfterEach
    fun tearDown(){
        postRepository.deleteAll()
    }

    @Test
    fun savePost_shouldSuccess(){
        // given
        val title = "title"
        val content = "content"
        val requestDto = PostSaveRequestDto(
            title = title,
            content = content,
        )
        mvc.perform(post("/api/v1/post")
            .contentType(MediaType.APPLICATION_JSON)
            .content(gson.toJson(requestDto)))
            .andExpectAll(
                status().isOk,
                content().contentType(MediaType.APPLICATION_JSON),
                content().string("1")
            )

        val post = postRepository.findAll().first()
        assertThat(post.id).isEqualTo(1L)
        assertThat(post.title).isEqualTo(title)
        assertThat(post.content).isEqualTo(content)
        assertThat(post.author).isNull()
    }

}