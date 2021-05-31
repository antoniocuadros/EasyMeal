package com.acl.easymeal.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Recetas")
data class Receta(
    @PrimaryKey val id:String,
    val nombreReceta:String,
    val imagen:String,
    val categoria:String,
    val ingrediente1:String,
    val ingrediente2:String,
    val ingrediente3:String,
    val ingrediente4:String,
    val ingrediente5:String,
    val ingrediente6:String,
    val cantidad_ingrediente1:String,
    val cantidad_ingrediente2:String,
    val cantidad_ingrediente3:String,
    val cantidad_ingrediente4:String,
    val cantidad_ingrediente5:String,
    val cantidad_ingrediente6:String,
    val ingrediente_principal:String,
    val paso1:String,
    val paso2:String,
    val paso3:String,
    val paso4:String,
    val paso5:String,
    val idAutor:String
)
