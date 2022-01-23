package com.taskagile.app.domain.application.commands

import org.springframework.util.Assert

data class RegistrationCommand(
    val username: String,
    val emailAddress: String,
    val password: String
) {
    init {
        Assert.hasText(username, "파라미터 username 은 빈 문자열일 수 없습니다.")
        Assert.hasText(emailAddress, "파라미터 emailAddress 는 빈 문자열일 수 없습니다.")
        Assert.hasText(password, "파라미터 password 는 빈 문자열일 수 없습니다.")
    }
}