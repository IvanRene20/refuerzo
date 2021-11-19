package com.rene.RefuerzoApi.service

import com.rene.RefuerzoApi.model.Docente
import com.rene.RefuerzoApi.repository.DocenteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DocenteService {
    @Autowired
    lateinit var docenteRepository: DocenteRepository


    fun list(): List<Docente> {

        return docenteRepository.findAll()
    }
    fun save(docente: Docente): Docente {

        return docenteRepository.save(docente)
    }
    fun update( docente: Docente): Docente {
        return docenteRepository.save(docente)
    }
    fun updateDescription (docente: Docente): Docente {
        val response = docenteRepository.findById(docente.id)
            ?: throw Exception()
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
