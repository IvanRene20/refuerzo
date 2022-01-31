package com.rene.RefuerzoApi.Service

import com.google.gson.Gson
import com.rene.RefuerzoApi.model.Estudiante
import com.rene.RefuerzoApi.repository.EstudianteRepository
import com.rene.RefuerzoApi.service.EstudianteService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
class EstudianteServiceEva {
    @InjectMocks
    lateinit var estudianteService: EstudianteService
    @Mock
    lateinit var estudianteRepository: EstudianteRepository


    val jsonString = File("./src/test/resources/Estudiante/createEstudiante.json").readText(Charsets.UTF_8)
    val EstudianteMock = Gson().fromJson(jsonString, Estudiante::class.java)

    val returnObject:Estudiante = Estudiante().apply{
        id = 1
        nombres = "rene Perez"
        apellidos = "perez perez"
    }
    val  newObject:Estudiante = Estudiante().apply {
        id = 1
        nombres = "  "
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
    fun UpdateIsFailedWhenNombresIsEmpty(){
        Assertions.assertThrows(Exception::class.java) {
            EstudianteMock.apply { nombres="  "}
            Mockito.`when`(estudianteRepository.save(Mockito.any(Estudiante::class.java))).thenReturn(null)
         val response =   estudianteService.updateDescription(EstudianteMock)
            Assertions.assertEquals(response.nombres, EstudianteMock.nombres)
        }

    }

}