package com.rene.RefuerzoApi.service

import com.rene.RefuerzoApi.model.Asignatura
import com.rene.RefuerzoApi.repository.AsignaturaRepository
import com.rene.RefuerzoApi.repository.DocenteRepository
import com.rene.RefuerzoApi.repository.EstudianteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class AsignaturaService {
    @Autowired
    lateinit var asignaturaRepository: AsignaturaRepository

    @Autowired
    lateinit var docenteRepository: DocenteRepository

    @Autowired
    lateinit var estudianteRepository: EstudianteRepository


    fun list(): List<Asignatura> {

        return asignaturaRepository.findAll()
    }

    fun save(asignatura: Asignatura): Asignatura {
        try {
            estudianteRepository.findById(asignatura.estudianteId)
                ?: throw  Exception("estudiante no existe")


            docenteRepository.findById(asignatura.docenteId)
                ?: throw Exception("docente no existe")

            asignatura.materia?.takeIf { it.trim().isNotEmpty() }
                ?: throw java.lang.Exception("Materia no puede estar en blanco")

        }
        catch (ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND,  ex.message)

        }
        return asignaturaRepository.save(asignatura)


    }


    fun update(asignatura: Asignatura): Asignatura {
        return asignaturaRepository.save(asignatura)
    }
    fun updateDescription (asignatura: Asignatura):Asignatura {
        try {
            val response = asignaturaRepository.findById(asignatura.id)
                ?: throw Exception("No se encuentra el id ")
            response.apply {
                this.materia = asignatura.materia
            }
            return asignaturaRepository.save(asignatura)
        }
        catch (ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND,  ex.message)

        }


    }


    fun delete (id:Long): Boolean{
        asignaturaRepository.deleteById(id)
        return true
    }

}