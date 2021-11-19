package com.rene.RefuerzoApi.model

import javax.persistence.*

@Entity
@Table(name = "docente")

class Docente {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null

    var nombres: String? = null
    var apellidos: String? = null
    var correo: String? = null
    var telefono: Int? = null
    var cedula: Int? = null
}