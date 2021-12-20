package com.rene.RefuerzoApi.service

import com.rene.RefuerzoApi.model.Docente
import com.rene.RefuerzoApi.repository.DocenteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class DocenteService {
    @Autowired
    lateinit var docenteRepository: DocenteRepository


    fun list(): List<Docente> {

        return docenteRepository.findAll()
    }
    fun save(docente: Docente): Docente {
        try {

            docente.nombres?.trim()?.isEmpty()
                ?: throw java.lang.Exception("nombres no puede estar en blanco")

            docente.apellidos?.trim()?.isEmpty()
                ?: throw java.lang.Exception("apellidos no puede estare en blanco")

        }
        catch (ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message
            )

        }


        return docenteRepository.save(docente)


    }
    fun update( docente: Docente): Docente {
        return docenteRepository.save(docente)
    }
    fun updateDescription (docente: Docente): Docente {
        val response = docenteRepository.findById(docente.id)
            ?: throw Exception("No se encuentra el Id")
        response.apply {
            this.nombres=docente.nombres
        }
        return docenteRepository.save(docente)
    }
    fun delete (id:Long): Boolean{
       docenteRepository.deleteById(id)
        return true
    }
}
