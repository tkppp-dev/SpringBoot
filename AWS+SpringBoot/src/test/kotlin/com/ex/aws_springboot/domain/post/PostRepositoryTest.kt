package com.ex.aws_springboot.domain.post

import com.ex.aws_springboot.domain.post.Post
import com.ex.aws_springboot.domain.post.PostRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired

@SpringBootTest
class PostRepositoryTest (
    @Autowired private val postRepository: PostRepository
    ) {

    @AfterEach
    fun cleanup(){
        postRepository.deleteAll()
    }

    @Test
    fun postRepository_findAfterSave_shouldSuccess(){
        val title = "테스트 게시글 제목"
        val content = "테스트 게시글 본문"
        val author = "tkppp"

        postRepository.save(Post(title = title, content = content, author = author))

        val postList = postRepository.findAll()
        val post = postList.first()

        assertThat(post.title).isEqualTo(title)
        assertThat(post.content).isEqualTo(content)
        assertThat(post.author).isEqualTo(author)
    }
}