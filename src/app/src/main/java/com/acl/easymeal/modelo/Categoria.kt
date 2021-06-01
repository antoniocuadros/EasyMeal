package com.acl.easymeal.modelo

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Categorias")
data class Categoria(
    @PrimaryKey val nombreCategoria:String,
    var imagen: Bitmap,
    val nombreImagen:String
)
