package com.taskagile.app.web.payload

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.validation.Validation
import javax.validation.Validator

import org.junit.jupiter.api.Assertions.assertEquals
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RegistrationPayloadTests() {
    lateinit var validator: Validator
    val log: Logger = LoggerFactory.getLogger(RegistrationPayloadTests::class.java)

    @BeforeEach
    fun setup(){
        val factory = Validation.buildDefaultValidatorFactory()
        validator = factory.validator
    }

    @Test
    fun validate_blankPayload_shouldFail(){
        val payload = RegistrationPayload()
        val violations = validator.validate(payload)
        assertEquals(3, violations.size)
    }
}