package com.rene.RefuerzoApi.service

import com.rene.RefuerzoApi.model.Estudiante
import com.rene.RefuerzoApi.repository.EstudianteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class EstudianteService {
    @Autowired
    lateinit var estudianteRepository: EstudianteRepository


    fun list(): List<Estudiante> {

        return estudianteRepository.findAll()
    }
    fun save(estudiante: Estudiante):Estudiante {

        return estudianteRepository.save(estudiante)
    }
    fun update( estudiante: Estudiante):Estudiante{
        return estudianteRepository.save(estudiante)
    }
    fun updateDescription (estudiante: Estudiante):Estudiante {
        val response = estudianteRepository.findById(estudiante.id)
            ?: throw Exception()
        response.apply {
            this.nombres=estudiante.nombres
        }
        return estudianteRepository.save(estudiante)
    }
    fun delete (id:Long): Boolean{
        estudianteRepository.deleteById(id)
        return true
    }

}