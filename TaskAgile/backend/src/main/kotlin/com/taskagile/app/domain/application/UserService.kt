package com.taskagile.app.domain.application

import com.taskagile.app.domain.application.commands.RegistrationCommand

interface UserService {
    fun register(command: RegistrationCommand)
}