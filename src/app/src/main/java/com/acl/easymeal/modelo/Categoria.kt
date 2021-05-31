package com.acl.easymeal.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Categorias")
data class Categoria(
    @PrimaryKey val nombreCategoria:String,
    val imagen:String
)
