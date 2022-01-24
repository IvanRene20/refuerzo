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

    @Test
    fun createAsignatura(){

        Mockito.`when`(estudianteRepository.findById(asignaturaMock.estudianteId)).thenReturn(estudianteMock)
        Mockito.`when`(docenteRepository.findById(asignaturaMock.docenteId)).thenReturn(docenteMock)
        val response = asignaturaService.save(asignaturaMock)
        Assertions.assertEquals(response.id, asignaturaMock.id)
        Assertions.assertEquals(response.materia, asignaturaMock.materia)
        Assertions.assertEquals(response.estudianteId, asignaturaMock.estudianteId)
        Assertions.assertEquals(response.docenteId, asignaturaMock.docenteId)

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


}