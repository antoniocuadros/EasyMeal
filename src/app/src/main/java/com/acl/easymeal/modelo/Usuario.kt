package com.acl.easymeal.modelo

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
/*
    Esta clase representa un usuario y define la tabla Usuarios de la base de datos.
 */
/*
    Los atributos de esta clase son:
        -> username: Nombre del usuario, de tipo String.
        -> password: Contraseña del usuario, de tipo String.
        -> imagen: Imagen del usuario, de tipo Bitmap.
 */
@Entity(tableName = "Usuarios")
data class Usuario(
    @PrimaryKey val username:String,
    val password:String,
    var imagen:Bitmap
)
