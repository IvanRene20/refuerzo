package com.rene.RefuerzoApi.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.ArrayList

@Service
class AppUserDetailsService : UserDetailsService {

    @Autowired
    lateinit var userService: UserService
    override fun loadUserByUsername(username: String?): UserDetails {
        val response = userService.getUser(username)
        return User(response?.username,"{noop}"+response?.password, ArrayList())
    }

}