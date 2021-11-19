package com.rene.RefuerzoApi.repository

import com.rene.RefuerzoApi.model.Docente
import com.rene.RefuerzoApi.model.Estudiante
import org.springframework.data.jpa.repository.JpaRepository

interface DocenteRepository:JpaRepository<Docente, Long> {
    fun findById(id: Long?): Docente?
}