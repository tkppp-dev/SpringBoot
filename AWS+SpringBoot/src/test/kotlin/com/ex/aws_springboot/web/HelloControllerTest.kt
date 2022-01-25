package com.ex.aws_springboot.web

import com.ex.aws_springboot.domain.post.TestService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [HelloController::class])
class HelloControllerTest(@Autowired private val mvc: MockMvc) {

    @MockBean
    private lateinit var ts: TestService

    @Test
    fun controller_return_hello(){
        val resp = "hello"
        mvc.perform(get("/hello"))
            .andExpect(status().isOk)
            .andExpect(content().string(resp))

    }
}