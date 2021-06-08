package com.acl.easymeal.modelo

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
    Esta clase representa un ingrediente y define la tabla Ingredientes de la base de datos.
 */
/*
    Los atributos de esta clase son:
        -> nombreIngrediente: Nombre del ingrediente, de tipo String.
        -> urlImagen: Nombre de la imagen asociada al ingrediente, de tipo String.
 */
@Entity(tableName = "Ingredientes")
data class Ingrediente(
    @PrimaryKey val nombreIngrediente:String,
    val urlImagen:String
)