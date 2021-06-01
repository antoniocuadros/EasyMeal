package com.acl.easymeal.modelo

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Usuarios")
data class Usuario(
    @PrimaryKey val username:String,
    val password:String,
    var imagen:Bitmap
)
