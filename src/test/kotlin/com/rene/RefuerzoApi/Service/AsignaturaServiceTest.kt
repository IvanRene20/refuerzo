package com.rene.RefuerzoApi.Service

import com.google.gson.Gson
import com.rene.RefuerzoApi.model.Asignatura
import com.rene.RefuerzoApi.model.Docente
import com.rene.RefuerzoApi.model.Estudiante
import com.rene.RefuerzoApi.repository.AsignaturaRepository
import com.rene.RefuerzoApi.repository.DocenteRepository
import com.rene.RefuerzoApi.repository.EstudianteRepository
import com.rene.RefuerzoApi.service.AsignaturaService
import com.rene.RefuerzoApi.service.EstudianteService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
class AsignaturaServiceTest {
    @InjectMocks
    lateinit var asignaturaService: AsignaturaService
    @Mock
    lateinit var asignaturaRepository: AsignaturaRepository
    @Mock
    lateinit var estudianteRepository: EstudianteRepository
    @Mock
    lateinit var docenteRepository: DocenteRepository




    val jsonString = File("./src/test/resources/Asignatura/crearAsignatura.json").readText(Charsets.UTF_8)
    val asignaturaMock = Gson().fromJson(jsonString, Asignatura::class.java)

    val jsonString1 = File("./src/test/resources/Docente/crearDocente.json").readText(Charsets.UTF_8)
    val docenteMock = Gson().fromJson(jsonString1, Docente::class.java)

    val jsonString2 = File("./src/test/resources/Estudiante/createEstudiante.json").readText(Charsets.UTF_8)
    val estudianteMock = Gson().fromJson(jsonString2, Estudiante::class.java)

    val returnObject:Asignatura = Asignatura().apply{
        id = 1
        materia = "matematica"
        estudianteId = 1
        docenteId = 1

    }
    val  newObject:Asignatura = Asignatura().apply {
        id = 1
        materia = "matematica"
        estudianteId = 1
        docenteId = 1
    }

    @Test
    fun createAsignatura(){
        Assertions.assertThrows(Exception::class.java) {
            Mockito.`when`(estudianteRepository.findById(newObject.estudianteId)).thenReturn(null)
            Mockito.`when`(docenteRepository.findById(newObject.docenteId)).thenReturn(null)
            val response = asignaturaService.save(asignaturaMock)
            Assertions.assertEquals(response.id, asignaturaMock.id)
            Assertions.assertEquals(response.materia, asignaturaMock.materia)
            Assertions.assertEquals(response.estudianteId, asignaturaMock.estudianteId)
            Assertions.assertEquals(response.docenteId, asignaturaMock.docenteId)
        }
    }

    @Test
    fun createAsignaturaFailedWhenMateriaIsEmpty(){
        Assertions.assertThrows(Exception::class.java) {
            asignaturaMock.apply { materia= "   "}
            Mockito.`when`(asignaturaRepository.save(Mockito.any(Asignatura::class.java))).thenReturn(asignaturaMock)
            asignaturaService.save(asignaturaMock)
        }

    }


    @Test
    fun createAsignaturaFailedWhenEStudianteIdIsEmpty(){
        Assertions.assertThrows(Exception::class.java) {
            asignaturaMock.apply { estudianteId= null}
            Mockito.`when`(asignaturaRepository.save(Mockito.any(Asignatura::class.java))).thenReturn(asignaturaMock)
            asignaturaService.save(asignaturaMock)
        }

    }

    @Test
    fun createAsignaturaFailedWhenDocenteIdIsEmpty(){
        Assertions.assertThrows(Exception::class.java) {
            asignaturaMock.apply { docenteId= null}
            Mockito.`when`(asignaturaRepository.save(Mockito.any(Asignatura::class.java))).thenReturn(asignaturaMock)
            asignaturaService.save(asignaturaMock)
        }

    }
    @Test
    fun updateIsCorrect(){
        Mockito.`when`(asignaturaRepository.findById(newObject.id)).thenReturn(returnObject)
        Mockito.`when`(asignaturaRepository.save(Mockito.any(Asignatura::class.java))).thenReturn(returnObject)
        val response = asignaturaService.update(newObject)
        Assertions.assertEquals(response.id, newObject.id)
    }
    @Test
    fun updateIsNotCorrect(){
        Assertions.assertThrows(Exception::class.java) {
            Mockito.`when`(asignaturaRepository.findById(newObject.id)).thenReturn(null)
            Mockito.`when`(asignaturaRepository.save(Mockito.any(Asignatura::class.java))).thenReturn(returnObject)
            val response = asignaturaService.update(newObject)
            Assertions.assertEquals(response.id, newObject.id)
        }
    }
    @Test
    fun updateDescriptionIsCorrect(){
        Mockito.`when`(asignaturaRepository.findById(newObject.id)).thenReturn(returnObject)
        Mockito.`when`(asignaturaRepository.save(Mockito.any(Asignatura::class.java))).thenReturn(returnObject)
        val response = asignaturaService.updateDescription(newObject)
        Assertions.assertEquals(response.id, newObject.id)
    }
    @Test
    fun updateDescriptionIsNotCorrect(){
        Assertions.assertThrows(Exception::class.java) {
            Mockito.`when`(asignaturaRepository.findById(newObject.id)).thenReturn(null)
            Mockito.`when`(asignaturaRepository.save(Mockito.any(Asignatura::class.java))).thenReturn(returnObject)
            val response = asignaturaService.updateDescription(newObject)
            Assertions.assertEquals(response.id, newObject.id)
        }
    }
    @Test
    fun delete(){
        Mockito.`when`(asignaturaRepository.findById(newObject.id)).thenReturn(returnObject)
        Mockito.`when`(asignaturaRepository.save(Mockito.any(Asignatura::class.java))).thenReturn(returnObject)
        val response = asignaturaService.delete(newObject.id)
        Assertions.assertEquals(response, true)
    }
    @Test
    fun deleteIsFailed(){
        Assertions.assertThrows(Exception::class.java) {
            Mockito.`when`(asignaturaRepository.findById(newObject.id)).thenReturn(null)
            Mockito.`when`(asignaturaRepository.save(Mockito.any(Asignatura::class.java))).thenReturn(returnObject)
            val response = asignaturaService.delete(newObject.id)
            Assertions.assertEquals(response, true)
        }
    }
}