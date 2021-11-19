package com.rene.RefuerzoApi.model


import javax.persistence.*

@Entity
@Table(name = "estudiante")

class Estudiante {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null

    var nombres: String? = null
    var apellidos: String? = null
    var curso: String? = null
    var telefono: Int? = null
    var cedula: Int? = null


}