package com.rene.RefuerzoApi.repository


import com.rene.RefuerzoApi.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun findById (id: Long?): User?
    fun findByUsername (username: String?): User?
}