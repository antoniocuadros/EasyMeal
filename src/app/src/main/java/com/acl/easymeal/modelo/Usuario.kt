package com.acl.easymeal.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Usuarios")
data class Usuario(
    @PrimaryKey val username:String,
    val password:String
)
