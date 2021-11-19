package com.rene.RefuerzoApi.Controller

import com.rene.RefuerzoApi.model.Docente
import com.rene.RefuerzoApi.service.DocenteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/Docente")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT])

class DocenteController {
    @Autowired
    lateinit var docenteService: DocenteService
    @GetMapping
    fun list(): List<Docente> {
        return docenteService.list()
    }
    @PostMapping
    fun save (@RequestBody docente: Docente): Docente {
        return docenteService.save(docente)
    }
    @PutMapping
    fun update (@RequestBody docente: Docente): Docente {
        return docenteService.update(docente)
    }
    @PatchMapping
    fun updateDescription (@RequestBody docente: Docente): Docente {
        return docenteService.updateDescription(docente)
    }
    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") id: Long): Boolean{
        return docenteService.delete(id)
    }

}