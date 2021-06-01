package com.acl.easymeal.modelo

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Ingredientes")
data class Ingrediente(
    @PrimaryKey val nombreIngrediente:String,
    val urlImagen:String
)