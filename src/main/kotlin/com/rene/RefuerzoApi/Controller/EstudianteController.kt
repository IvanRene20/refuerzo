package com.rene.RefuerzoApi.Controller

import com.rene.RefuerzoApi.model.Estudiante
import com.rene.RefuerzoApi.service.EstudianteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/Estudiante")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT])

class EstudianteController {
    @Autowired
    lateinit var estudianteService: EstudianteService
    @GetMapping
    fun list(): List<Estudiante> {
        return estudianteService.list()
    }
    @PostMapping
    fun save (@RequestBody  estudiante: Estudiante): Estudiante{
        return estudianteService.save(estudiante)
    }
    @PutMapping
    fun update (@RequestBody estudiante: Estudiante): Estudiante{
        return estudianteService.update(estudiante)
    }
    @PatchMapping
    fun updateDescription (@RequestBody estudiante: Estudiante):Estudiante{
        return estudianteService.updateDescription(estudiante)
    }
    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") id: Long): Boolean{
        return estudianteService.delete(id)
    }
}
