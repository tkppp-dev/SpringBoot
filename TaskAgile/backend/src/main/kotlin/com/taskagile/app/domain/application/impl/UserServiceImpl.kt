package com.taskagile.app.domain.application.impl

import com.taskagile.app.domain.application.UserService
import com.taskagile.app.domain.application.commands.RegistrationCommand
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class UserServiceImpl(
) : UserService {

    override fun register(command: RegistrationCommand) {

    }
}