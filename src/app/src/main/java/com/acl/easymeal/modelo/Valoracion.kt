package com.acl.easymeal.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Valoraciones")
data class Valoracion(
    @PrimaryKey val idReceta:String,
    val valoracionMedia:String,
    val totalValoraciones:ArrayList<Int>
)