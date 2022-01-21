package com.taskagile.app.web.payload

import org.apache.commons.lang3.RandomStringUtils
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.validation.Validation
import javax.validation.Validator

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest
import org.assertj.core.api.Assertions.assertThat

@SpringBootTest
class RegistrationPayloadTests() {
    lateinit var validator: Validator
    val log: Logger = LoggerFactory.getLogger(RegistrationPayloadTests::class.java)

    @BeforeEach
    fun setup() {
        val factory = Validation.buildDefaultValidatorFactory()
        validator = factory.validator
    }

    @Test
    fun validate_blankPayload_shouldFail() {
        val payload = RegistrationPayload()
        val violations = validator.validate(payload)

        assertThat(violations.size).isEqualTo(3)
    }

    @Test
    fun validate_payloadWithInvalidEmail_shouldFail() {
        val payload = RegistrationPayload(
            emailAddress = "invalidEmail",
            username = "validUsername",
            password = "validPassword"
        )
        val violations = validator.validate(payload)

        assertThat(violations.size).isEqualTo(1)
        assertThat(violations.first().message).isEqualTo("이메일 형식이 올바라야 한다.")
    }

    @Test
    fun validate_payloadWithEmailAddressLongerThan100_shouldFail() {
        val signature = RandomStringUtils.random(50, true, true)
        val domain = RandomStringUtils.random(50, true, true) + ".com"
        val email = "$signature@$domain"

        val payload = RegistrationPayload(
            emailAddress = email,
            username = "validUsername",
            password = "validPassword"
        )
        val violations = validator.validate(payload)
        assertThat(violations.size).isEqualTo(1)
        assertThat(violations.first().message).isEqualTo("이메일 주소는 최대 100자 이하여야 한다.")
    }

    @Test
    fun validate_payloadWithUsernameShorterThan2_shouldFail() {
        val payload = RegistrationPayload(
            emailAddress = "valid@email.com",
            username = RandomStringUtils.random(1, true, true),
            password = "validPassword"
        )
        val violations = validator.validate(payload)
        assertThat(violations.size).isEqualTo(1)
        assertThat(violations.first().message).isEqualTo("사용자 이름은 2자 이상, 50자 이하여야 한다.")
    }

    @Test
    fun validate_payloadWittUsernameLongerThan50_shouldFail(){
        val payload = RegistrationPayload(
            emailAddress = "valid@email.com",
            username = RandomStringUtils.random(51, true, true),
            password = "validPassword"
        )
        val violations = validator.validate(payload)
        assertThat(violations.size).isEqualTo(1)
        assertThat(violations.first().message).isEqualTo("사용자 이름은 2자 이상, 50자 이하여야 한다.")
    }

    @Test
    fun validate_payloadWithPasswordShorterThan6_shouldFail() {
        val payload = RegistrationPayload(
            emailAddress = "valid@email.com",
            username = "validUsername",
            password = RandomStringUtils.random(5, true, true)
        )
        val violations = validator.validate(payload)
        assertThat(violations.size).isEqualTo(1)
        assertThat(violations.first().message).isEqualTo("비밀번호는 6자 이상, 30자 이하여야 한다.")
    }

    @Test
    fun validate_payloadWithPasswordLongerThan30_shouldFail() {
        val payload = RegistrationPayload(
            emailAddress = "valid@email.com",
            username = "validUsername",
            password = RandomStringUtils.random(31, true, true)
        )
        val violations = validator.validate(payload)
        assertThat(violations.size).isEqualTo(1)
        assertThat(violations.first().message).isEqualTo("비밀번호는 6자 이상, 30자 이하여야 한다.")
    }
}