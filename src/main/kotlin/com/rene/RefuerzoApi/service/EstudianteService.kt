package com.rene.RefuerzoApi.service

import com.rene.RefuerzoApi.model.Estudiante
import com.rene.RefuerzoApi.repository.EstudianteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class EstudianteService {
    @Autowired
    lateinit var estudianteRepository: EstudianteRepository


    fun list(): List<Estudiante> {

        return estudianteRepository.findAll()
    }

    fun save(estudiante: Estudiante): Estudiante {
        try {

         if (estudiante.nombres.equals("")  ) {
                 throw Exception("Por favor llene los espacios en blanco")

        }
            if (estudiante.apellidos.equals("") ) {
                throw Exception("Por favor llene los espacios en blanco")

    }
            else {
        return estudianteRepository.save(estudiante)
    }

        }
        catch (ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message
            )

        }

    }
        fun update(estudiante: Estudiante): Estudiante {
            return estudianteRepository.save(estudiante)
        }

        fun updateDescription(estudiante: Estudiante): Estudiante {
            val response = estudianteRepository.findById(estudiante.id)
                ?: throw Exception("No se encuentra el Id")
            response.apply {
                this.nombres = estudiante.nombres
            }
            return estudianteRepository.save(estudiante)
        }

        fun delete(id: Long): Boolean {
            estudianteRepository.deleteById(id)
            return true
        }

    }
