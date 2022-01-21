package com.taskagile.app.web.payload

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class RegistrationPayload(
    @field:Size(min = 2, max = 50, message = "사용자 이름은 2자 이상, 50자 이하여야 한다.")
    @field:NotEmpty
    var username: String? = null,

    @field:Email(message = "이메일 형식이 올바러야 한다.")
    @field:Size(max = 100, message = "이메일 주소는 최대 100자 이하여야 한다.")
    @field:NotEmpty
    var emailAddress: String? = null,

    @field:Size(min = 6, max = 30, message = "비밀번호는 6자 이상, 30자 이하여야 한다.")
    @field:NotEmpty
    var password: String? = null
)

