package com.acl.easymeal.modelo

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
    Esta clase representa una receta y define la tabla Recetas de la base de datos.
 */
/*
    Los atributos de esta clase son:
        -> nombreReceta: Nombre de la receta, de tipo String.
        -> descripcion: Descripción de la receta, de tipo String.
        -> imagen_text: Nombre de la imagen, de tipo String.
        -> imagen: Imagen principal de la receta, de tipo Bitmap.
        -> categoria: Categoría de la receta, de tipo String.
        -> ingrediente1...ingrediente12: Ingredientes de la receta, de tipo String.
        -> cantidad_ingrediente1...cantidad_ingrediente12: Cantidades de cada ingrediente, de tipo String.
        -> duracion: Duración de la preparación de la receta, de tipo Int.
        -> paso1...paso20: Pasos de la receta, de tipo String.
        -> imagen1...imagen20: Imágenes de los pasos de la receta, de tipo Bitmap.
        -> idAutor: ID del autor de la receta, de tipo String.
        -> num_ingredientes: Número de ingredientes de la receta, de tipo Int.
        -> num_pasos: Número de pasos de la receta, de tipo Int.
        -> dificultad: Dificultad de la receta, de tipo String.
        -> thermomix Indica si hace falta thermomix, de tipo Boolean.
 */
@Entity(tableName = "Recetas")
data class Receta(
    @PrimaryKey(autoGenerate = true) val id:Int,
    val nombreReceta:String,
    val descripcion:String,
    val imagen_text:String,
    var imagen:Bitmap,
    val categoria:String,
    val ingrediente1:String,
    val ingrediente2:String,
    val ingrediente3:String,
    val ingrediente4:String,
    val ingrediente5:String,
    val ingrediente6:String,
    val ingrediente7:String,
    val ingrediente8:String,
    val ingrediente9:String,
    val ingrediente10:String,
    val ingrediente11:String,
    val ingrediente12:String,
    val cantidad_ingrediente1:String,
    val cantidad_ingrediente2:String,
    val cantidad_ingrediente3:String,
    val cantidad_ingrediente4:String,
    val cantidad_ingrediente5:String,
    val cantidad_ingrediente6:String,
    val cantidad_ingrediente7:String,
    val cantidad_ingrediente8:String,
    val cantidad_ingrediente9:String,
    val cantidad_ingrediente10:String,
    val cantidad_ingrediente11:String,
    val cantidad_ingrediente12:String,
    val duracion:Int,
    val paso1:String,
    var imagen1:Bitmap? = null,
    val paso2:String,
    var imagen2:Bitmap? = null,
    val paso3:String,
    var imagen3:Bitmap? = null,
    val paso4:String,
    var imagen4:Bitmap? = null,
    val paso5:String,
    var imagen5:Bitmap? = null,
    val paso6:String,
    var imagen6:Bitmap? = null,
    val paso7:String,
    var imagen7:Bitmap? = null,
    val paso8:String,
    var imagen8:Bitmap? = null,
    val paso9:String,
    var imagen9:Bitmap? = null,
    val paso10:String,
    var imagen10:Bitmap? = null,
    val paso11:String,
    var imagen11:Bitmap? = null,
    val paso12:String,
    var imagen12:Bitmap? = null,
    val paso13:String,
    var imagen13:Bitmap? = null,
    val paso14:String,
    var imagen14:Bitmap? = null,
    val paso15:String,
    var imagen15:Bitmap? = null,
    val paso16:String,
    var imagen16:Bitmap? = null,
    val paso17:String,
    var imagen17:Bitmap? = null,
    val paso18:String,
    var imagen18:Bitmap? = null,
    val paso19:String,
    var imagen19:Bitmap? = null,
    val paso20:String,
    var imagen20:Bitmap? = null,
    val idAutor:String,
    var num_ingredientes:Int,
    var num_pasos:Int,
    var dificultad:String,
    var thermomix:Boolean
)
