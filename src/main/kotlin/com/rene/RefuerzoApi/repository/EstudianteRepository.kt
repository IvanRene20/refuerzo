package com.rene.RefuerzoApi.repository

import com.rene.RefuerzoApi.model.Estudiante
import org.springframework.data.jpa.repository.JpaRepository


interface EstudianteRepository:JpaRepository<Estudiante,Long> {
    fun findById(id: Long?): Estudiante?

}