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


            docente.nombres?.takeIf { it.trim().isNotEmpty() }
                ?: throw java.lang.Exception("nombres no puede estar en blanco")

            docente.apellidos?.takeIf { it.trim().isNotEmpty() }
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

    fun validateCedula(cedula:String): Boolean{
      if (cedula.length == 10){

          val DiReg = cedula.substring(0,2)
          val num1 = cedula.substring(0,1)
          val num2 = cedula.substring(1,2)
          val num3 = cedula.substring(2,3)
          val num4 = cedula.substring(3,4)
          val num5 = cedula.substring(4,5)
          val num6 = cedula.substring(5,6)
          val num7 = cedula.substring(6,7)
          val num8 = cedula.substring(7,8)
          val num9 = cedula.substring(8,9)
          val num10 = cedula.substring(9,10)
          if (DiReg.toInt() >= 1 && DiReg.toInt() <= 24){
              return true
          }
          val digpar = num2.toInt() + num4.toInt() + num6.toInt() + num8.toInt()

          val digimp = num1.toInt() * 2
          if (digimp > 9){
              digimp - 9
          }
          var digimp1 = num3.toInt() * 2
          if (digimp1 > 9){
                  digimp1 -= 9
          }

          var digimp2 = num5.toInt() * 2
          if (digimp2 > 9){
              digimp2 -= 9
          }
          var digimp3 = num7.toInt() * 2
          if (digimp3 > 9){
              digimp3 -= 9
          }
          var digimp4 = num9.toInt() * 2
          if (digimp4 > 9){
              digimp4 -= 9
          }

          val impar = digimp + digimp1 + digimp2 + digimp3 + digimp4
          val sumTotal = digpar + impar

          val decena = (sumTotal/10) + 1
          var digitoValidador = (decena*10)  - sumTotal
          if( digitoValidador == 10){
                  digitoValidador = 0
          }
          if (digitoValidador != 10){
                  return false
          }
      }
          return true
    }

}
