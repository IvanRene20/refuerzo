package com.rene.RefuerzoApi.service


import com.rene.RefuerzoApi.model.User
import com.rene.RefuerzoApi.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.server.ResponseStatusException


@Service
class UserService {
    @Autowired
    lateinit var userRepository: UserRepository


    fun list(): List<User> {
        return userRepository.findAll()
    }

    fun getUser (username:String?):User? {
        try {
            val response = userRepository.findByUsername(username)
                ?: throw Exception("No existe usuario")
            return response
        } catch(ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }

    fun update (@RequestBody user: User): User {
        return userRepository.save(user)
    }



}