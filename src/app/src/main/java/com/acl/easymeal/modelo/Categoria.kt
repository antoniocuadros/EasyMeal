package com.acl.easymeal.modelo

import androidx.room.PrimaryKey

data class Categoria(
    @PrimaryKey val nombreCategoria:String,
    val imagen:String
)
