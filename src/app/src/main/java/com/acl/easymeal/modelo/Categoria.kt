package com.acl.easymeal.modelo

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
    Esta clase representa una categoría de recetas y define la tabla Categorias de la base de datos.
 */
/*
    Los atributos de esta clase son:
        -> nombreCategoria: Nombre de la categoría, de tipo String.
        -> imagen: Imagen almacenada como Bitmap, de tipo Bitmap.
        -> nombreImagen: Nombre de la imagen almacenada, necesario para cargar inicialmente fotos de recetas por defecto, de tipo String.
 */
@Entity(tableName = "Categorias")
data class Categoria(
    @PrimaryKey val nombreCategoria:String,
    var imagen: Bitmap,
    val nombreImagen:String
)
