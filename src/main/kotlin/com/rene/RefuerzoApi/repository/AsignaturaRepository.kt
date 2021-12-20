package com.rene.RefuerzoApi.repository

import com.rene.RefuerzoApi.model.Asignatura
import org.springframework.data.jpa.repository.JpaRepository

interface AsignaturaRepository:JpaRepository<Asignatura,Long> {
    fun findById(id: Long?): Asignatura?
}