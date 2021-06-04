package com.acl.easymeal.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Valoraciones")
data class Valoracion(
    @PrimaryKey(autoGenerate = true) val idValoracion:Int,
    val idReceta:String,
    val valoracion:Int,
    val idusuario:String
)