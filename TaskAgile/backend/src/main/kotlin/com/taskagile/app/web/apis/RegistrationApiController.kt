package com.taskagile.app.web.apis

import com.taskagile.app.domain.application.UserService
import org.springframework.web.bind.annotation.RestController

@RestController
class RegistrationApiController(
    private val service: UserService
) {
}