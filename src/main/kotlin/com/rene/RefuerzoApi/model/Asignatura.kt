package com.rene.RefuerzoApi.model
import javax.persistence.*

@Entity
@Table(name = "asignatura")

class Asignatura {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null

    var materia: String? = null
    @Column(name = "estudiante_id")
    var estudianteId: Long? = null
    @Column(name = "docente_id")
    var docenteId: Long? = null

}