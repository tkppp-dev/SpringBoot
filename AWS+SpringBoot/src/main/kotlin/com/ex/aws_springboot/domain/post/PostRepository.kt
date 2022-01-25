package com.ex.aws_springboot.domain.post

import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long>{
}