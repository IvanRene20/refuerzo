package com.rene.RefuerzoApi.Controller

import com.rene.RefuerzoApi.model.Asignatura
import com.rene.RefuerzoApi.service.AsignaturaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/Asignatura")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT])

class AsignaturaController {
    @Autowired
    lateinit var asignaturaService: AsignaturaService
    @GetMapping
    fun list(): List<Asignatura> {
        return asignaturaService.list()
    }
    @PostMapping
    fun save (@RequestBody asignatura: Asignatura): Asignatura {
        return asignaturaService.save(asignatura)
    }
    @PutMapping
    fun update (@RequestBody asignatura: Asignatura): Asignatura {
        return asignaturaService.update(asignatura)
    }
    @PatchMapping
    fun updateDescription (@RequestBody asignatura: Asignatura): Asignatura {
        return asignaturaService.updateDescription(asignatura)
    }
    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") id: Long): Boolean{
        return asignaturaService.delete(id)
    }

}