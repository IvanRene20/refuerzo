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

            estudiante.nombres?.takeIf { it.trim().isNotEmpty() }
                ?: throw java.lang.Exception("nombre no puede ser vacio")


            estudiante.apellidos?.takeIf { it.trim().isNotEmpty() }
                ?: throw java.lang.Exception("apellidos no puede ser vacio")

        } catch (ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message
            )

        }
    return estudianteRepository.save(estudiante)
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

    fun WordSize(nombres: String?):Boolean {
      if (nombres?.length!!<10){
     return false

         }
        return true

    }

fun calcMultiplication(index:Int, number: Int):Int{
    //si index es par devolver numero por 2
    //si index es impar devolver numero por 1
    if (index%2==0){
        return index * 2
    }else{
        return index * 1
    }

}

    fun restNine(number: Int):Int {
        //si number es mayor o igual a 10 restamos 9
        if(number >= 10){
            return number -9
        }
        return number
    }

        fun subtacFromNextTen(index: Int):Int{
         var decena = index/10 +1
            var total = decena*10 - index
            return total
        }

}

