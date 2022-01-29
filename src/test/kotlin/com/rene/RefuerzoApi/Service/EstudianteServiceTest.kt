package com.rene.RefuerzoApi.Service

import com.google.gson.Gson
import com.rene.RefuerzoApi.model.Docente
import com.rene.RefuerzoApi.model.Estudiante
import com.rene.RefuerzoApi.repository.EstudianteRepository
import com.rene.RefuerzoApi.service.EstudianteService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
class EstudianteServiceTest {
    @InjectMocks
    lateinit var estudianteService: EstudianteService
    @Mock
    lateinit var estudianteRepository: EstudianteRepository

    val jsonString = File("./src/test/resources/Estudiante/createEstudiante.json").readText(Charsets.UTF_8)
    val EstudianteMock = Gson().fromJson(jsonString, Estudiante::class.java)

    @Test
    fun calculateMUltiplicacionIfISPair (){
        val response= estudianteService.calcMultiplication(2,2)
        Assertions.assertEquals(4,response)
    }
    @Test
    fun calculateMUltiplicacionIfISNotPair (){
        val response= estudianteService.calcMultiplication(1,2)
        Assertions.assertEquals(1,response)
    }
    @Test
    fun restNineMayoraTen(){
        //si number es mayor o igual a 10 restamos 9
        val response = estudianteService.restNine(11)
        Assertions.assertEquals(2,response)
    }
    @Test
    fun restNineMenoraTen(){
        //si number es mayor o igual a 10 restamos 9
        val response = estudianteService.restNine(9)
        Assertions.assertEquals(9,response)
    }

    @Test
    fun subtacFromNExtTEnXXX(){
        //15--> 20-15 =5
        //18-->20_18= 2
        //27-->30 30_27=3
        //6-->4
        //15/10 (int) =1.5 +1 * 10 +10 = 30

        val response = estudianteService.subtacFromNextTen(15)
        Assertions.assertEquals(5,response)
    }

    @Test
   fun createEstudiante(){
        Mockito.`when`(estudianteRepository.save(Mockito.any(Estudiante::class.java))).thenReturn(EstudianteMock)
        val response = estudianteService.save(EstudianteMock)
        Assertions.assertEquals(response.id, EstudianteMock.id)
        Assertions.assertEquals(response.nombres, EstudianteMock.nombres)
        Assertions.assertEquals(response.apellidos, EstudianteMock.apellidos)

   }

    @Test
    fun createEstudianteFailedWhenNombresIsEmpty(){
        Assertions.assertThrows(Exception::class.java) {
            EstudianteMock.apply { nombres=" "}
            Mockito.`when`(estudianteRepository.save(Mockito.any(Estudiante::class.java))).thenReturn(EstudianteMock)
            estudianteService.save(EstudianteMock)
        }

    }

    @Test
    fun createEstudianteFailedWhenApellidosIsEmpty(){
        Assertions.assertThrows(Exception::class.java) {
            EstudianteMock.apply { apellidos=" "}
            Mockito.`when`(estudianteRepository.save(Mockito.any(Estudiante::class.java))).thenReturn(EstudianteMock)
            estudianteService.save(EstudianteMock)
        }

    }
    val returnObject:Estudiante = Estudiante().apply{
        id = 1
        nombres = "rene Perez"
        apellidos = "perez perez"
    }
    val  newObject:Estudiante = Estudiante().apply {
        id = 1
        nombres = "rene Perez"
        apellidos = "perez perez"
    }

    @Test
    fun updateIsCorrect(){
        Mockito.`when`(estudianteRepository.findById(newObject.id)).thenReturn(returnObject)
        Mockito.`when`(estudianteRepository.save(Mockito.any(Estudiante::class.java))).thenReturn(returnObject)
        val response = estudianteService.update(newObject)
        Assertions.assertEquals(response.id, newObject.id)
    }

    @Test
    fun updateIsFailedWhen(){
        Assertions.assertThrows(Exception::class.java) {
            Mockito.`when`(estudianteRepository.findById(newObject.id)).thenReturn(null)
            Mockito.`when`(estudianteRepository.save(Mockito.any(Estudiante::class.java))).thenReturn(returnObject)
            val response = estudianteService.update(newObject)
            Assertions.assertEquals(response.id, newObject.id)
        }
    }
    @Test
    fun updateDescriptionIsCorrect(){
        Mockito.`when`(estudianteRepository.findById(newObject.id)).thenReturn(returnObject)
        Mockito.`when`(estudianteRepository.save(Mockito.any(Estudiante::class.java))).thenReturn(returnObject)
        val response = estudianteService.updateDescription(newObject)
        Assertions.assertEquals(response.id, newObject.id)
    }
    @Test
    fun updateDescriptionIsFailedWhen(){
        Assertions.assertThrows(Exception::class.java) {
            Mockito.`when`(estudianteRepository.findById(newObject.id)).thenReturn(null)
            Mockito.`when`(estudianteRepository.save(Mockito.any(Estudiante::class.java))).thenReturn(returnObject)
            val response = estudianteService.updateDescription(newObject)
            Assertions.assertEquals(response.id, newObject.id)
        }
    }
    @Test
    fun delete(){
        Mockito.`when`(estudianteRepository.findById(newObject.id)).thenReturn(returnObject)
        Mockito.`when`(estudianteRepository.save(Mockito.any(Estudiante::class.java))).thenReturn(returnObject)
        val response = estudianteService.delete(newObject.id)
        Assertions.assertEquals(response, true)
    }
    @Test
    fun deleteIsFailed(){
        Assertions.assertThrows(Exception::class.java) {
            Mockito.`when`(estudianteRepository.findById(newObject.id)).thenReturn(null)
            Mockito.`when`(estudianteRepository.save(Mockito.any(Estudiante::class.java))).thenReturn(returnObject)
            val response = estudianteService.delete(newObject.id)
            Assertions.assertEquals(response, true)
        }
    }


}