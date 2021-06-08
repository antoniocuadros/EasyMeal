package com.acl.easymeal.modelo

import androidx.room.*

/*
    Esta interfaz representa el DAO de la clase Valoración, es decir, a través de los métodos de esta
    interfaz accederemos a los datos de la tabla Valoraciones de la base de datos.
 */
@Dao
interface ValoracionDao {
    /*
        Este método inserta una valoración.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertaUna(item:Valoracion)

    /*
        Este método actualiza una valoración.
     */
    @Update
    fun actualizaValoracion(item:Valoracion)

    /*
        Este método obtiene las valoraciones de una receta.
     */
    @Query("SELECT * FROM Valoraciones WHERE idReceta= :buscar")
    fun obtenerPorNombreReceta(buscar:String):MutableList<Valoracion>

    /*
        Este método obtiene todas las valoraciones.
     */
    @Query("SELECT * FROM Valoraciones")
    fun obtenerTodas():MutableList<Valoracion>

    /*
        Este método obtiene la valoración de un usuario a una receta
     */
    @Query("SELECT * FROM Valoraciones WHERE idusuario= :buscar and idReceta= :idreceta")
    fun obtenerPorNombreUsuario(buscar:String, idreceta:String):MutableList<Valoracion>
}