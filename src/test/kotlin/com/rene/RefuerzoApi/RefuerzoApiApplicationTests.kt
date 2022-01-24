package com.rene.RefuerzoApi


import com.rene.RefuerzoApi.model.Estudiante
import com.rene.RefuerzoApi.service.DocenteService
import com.rene.RefuerzoApi.service.EstudianteService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RefuerzoApiApplicationTests {

	 @Autowired
	 lateinit var estudianteService: EstudianteService
	 @Autowired
	 lateinit var docenteService: DocenteService

	@Test
	fun contextLoads() {
	}

	@Test
	fun CheckWordSizeIsIncorrect(){
     val response:Boolean = estudianteService.WordSize("ABC")
		Assertions.assertEquals(false,response)
	}
	@Test
	fun CheckWordSizeIsCorrect(){
		val response:Boolean = estudianteService.WordSize("ABCDFGHIJKMNL")
		Assertions.assertEquals(true,response)
	}

}
