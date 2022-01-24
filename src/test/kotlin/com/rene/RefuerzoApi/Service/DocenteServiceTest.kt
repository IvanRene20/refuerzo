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
            docenteMock.apply { nombres=" "}
          Mockito.`when`(docenteRepository.save(Mockito.any(Docente::class.java))).thenReturn(docenteMock)
            docenteService.save(docenteMock)
        }

    }
    @Test
    fun createDocenteFailedWhenApellidosIsEmpty(){
        Assertions.assertThrows(Exception::class.java) {
            docenteMock.apply { apellidos="wert"}
            Mockito.`when`(docenteRepository.save(Mockito.any(Docente::class.java))).thenReturn(docenteMock)
            docenteService.save(docenteMock)
        }

    }
}