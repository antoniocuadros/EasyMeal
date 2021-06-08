package com.acl.easymeal.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
    Esta clase representa una valoración y define la tabla Valoraciones de la base de datos.
 */
/*
    Los atributos de esta clase son:
        -> idValoracion: ID de la valoración, de tipo Int.
        -> idReceta: ID de la receta a la que pertenece la valoración, de tipo Int.
        -> valoracion: Valoración numérica de 1 a 5, de tipo Int.
        -> idusuario: ID del usuario que ha valorado la receta, de tipo String.
 */
@Entity(tableName = "Valoraciones")
data class Valoracion(
    @PrimaryKey(autoGenerate = true) val idValoracion:Int,
    val idReceta:String,
    val valoracion:Int,
    val idusuario:String
)