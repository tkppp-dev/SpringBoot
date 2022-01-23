package com.ex.aws_springboot.web.domain.posts

import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long>{
}