package com.rene.RefuerzoApi.Service

import com.google.gson.Gson
import com.rene.RefuerzoApi.model.Docente
import com.rene.RefuerzoApi.repository.DocenteRepository
import com.rene.RefuerzoApi.service.DocenteService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
class DocenteServiceTest {

    @InjectMocks
    lateinit var docenteService: DocenteService
    @Mock
    lateinit var docenteRepository: DocenteRepository

    val jsonString = File("./src/test/resources/Docente/crearDocente.json").readText(Charsets.UTF_8)
    val docenteMock = Gson().fromJson(jsonString, Docente::class.java)



    @Test
    fun validateCedulaEcu(){
        val response = docenteService.validateCedula("0105109268")
        Assertions.assertEquals(true,response)
    }

   /* val  returnObject:Docente = Docente().apply {
        id = 1
        nombres = "rene ivan"
        apellidos = "perez perez"
    }
    val  newObject:Docente = Docente().apply {
        id = 1
        nombres = "rene Perez"
        apellidos = "perez perez"
    }*/


    @Test
    fun createDocente(){
        Mockito.`when`(docenteRepository.save(Mockito.any(Docente::class.java))).thenReturn(docenteMock)
        val response = docenteService.save(docenteMock)
        Assertions.assertEquals(response.id, docenteMock.id)
        Assertions.assertEquals(response.nombres, docenteMock.nombres)
        Assertions.assertEquals(response.apellidos, docenteMock.apellidos)

    }

    @Test
    fun createDocenteFailedWhenNombresIsEmpty(){
        Assertions.assertThrows(Exception::class.java) {
            docenteMock.apply { nombres="   "}
          Mockito.`when`(docenteRepository.save(Mockito.any(Docente::class.java))).thenReturn(docenteMock)
            docenteService.save(docenteMock)
        }

    }
    @Test
    fun createDocenteFailedWhenApellidosIsEmpty(){
        Assertions.assertThrows(Exception::class.java) {
            docenteMock.apply { apellidos=""}
            Mockito.`when`(docenteRepository.save(Mockito.any(Docente::class.java))).thenReturn(docenteMock)
            docenteService.save(docenteMock)
        }

    }
    val returnObject:Docente = Docente().apply{
        id = 1
        nombres = "rene Perez"
        apellidos = "perez perez"
    }
    val  newObject:Docente = Docente().apply {
        id = 1
        nombres = "rene Perez"
        apellidos = "perez perez"
    }

    @Test
    fun updateIsCorrect(){
        Mockito.`when`(docenteRepository.findById(newObject.id)).thenReturn(returnObject)
        Mockito.`when`(docenteRepository.save(Mockito.any(Docente::class.java))).thenReturn(returnObject)
        val response = docenteService.update(newObject)
        Assertions.assertEquals(response.id, newObject.id)

    }
    @Test
    fun updateIsFailedWhen(){
        Assertions.assertThrows(Exception::class.java) {
            Mockito.`when`(docenteRepository.findById(newObject.id)).thenReturn(null)
            Mockito.`when`(docenteRepository.save(Mockito.any(Docente::class.java))).thenReturn(returnObject)
            val response = docenteService.update(newObject)
            Assertions.assertEquals(response.id, newObject.id)
        }

    }
    @Test
    fun updateDescriptionIsCorrect(){
        Mockito.`when`(docenteRepository.findById(newObject.id)).thenReturn(returnObject)
        Mockito.`when`(docenteRepository.save(Mockito.any(Docente::class.java))).thenReturn(returnObject)
        val response = docenteService.updateDescription(newObject)
        Assertions.assertEquals(response.id, newObject.id)

    }


    @Test
    fun updateDescriptionIsFailedWhen(){
        Assertions.assertThrows(Exception::class.java) {
            Mockito.`when`(docenteRepository.findById(newObject.id)).thenReturn(null)
            Mockito.`when`(docenteRepository.save(Mockito.any(Docente::class.java))).thenReturn(returnObject)
            val response = docenteService.updateDescription(newObject)
            Assertions.assertEquals(response.id, newObject.id)
        }

    }
    @Test
    fun delete(){
        Mockito.`when`(docenteRepository.findById(newObject.id)).thenReturn(returnObject)
        Mockito.`when`(docenteRepository.save(Mockito.any(Docente::class.java))).thenReturn(returnObject)
        val response = docenteService.delete(newObject.id)
        Assertions.assertEquals(response, true)

    }
    @Test
    fun deleteIsFailed(){
        Assertions.assertThrows(Exception::class.java) {
            Mockito.`when`(docenteRepository.findById(newObject.id)).thenReturn(null)
            Mockito.`when`(docenteRepository.save(Mockito.any(Docente::class.java))).thenReturn(returnObject)
            val response = docenteService.delete(newObject.id)
            Assertions.assertEquals(response, true)
        }
    }

}