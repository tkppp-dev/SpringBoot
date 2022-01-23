package com.taskagile.app.domain.model.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<Long, String>{
    fun findByUsername(username: String)
}