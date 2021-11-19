package com.rene.RefuerzoApi.service

import com.rene.RefuerzoApi.model.Asignatura
import com.rene.RefuerzoApi.repository.AsignaturaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AsignaturaService {
    @Autowired
    lateinit var asignaturaRepository: AsignaturaRepository


    fun list(): List<Asignatura> {

        return asignaturaRepository.findAll()
    }
    fun save(asignatura: Asignatura): Asignatura {

        return asignaturaRepository.save(asignatura)
    }
    fun update(asignatura: Asignatura): Asignatura {
        return asignaturaRepository.save(asignatura)
    }
    fun updateDescription (asignatura: Asignatura):Asignatura {
        val response = asignaturaRepository.findById(asignatura.id)
            ?: throw Exception()
        response.apply {
            this.materia=asignatura.materia
        }
        return asignaturaRepository.save(asignatura)
    }
    fun delete (id:Long): Boolean{
        asignaturaRepository.deleteById(id)
        return true
    }

}