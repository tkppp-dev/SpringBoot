package com.taskagile.app.web.apis

import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@WebMvcTest(RegistrationApiController::class)
class RegistrationApiControllerTest(private val mvc: MockMvc) {

    @Test
    fun register_blankPayload_shouldFailAndReturn400(){
        mvc.perform(post("/api/registrations"))
            .andExpect((status().`is`(400)))
    }
}