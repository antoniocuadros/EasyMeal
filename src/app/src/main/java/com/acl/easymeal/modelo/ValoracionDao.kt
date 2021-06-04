package com.acl.easymeal.modelo

import androidx.room.*

@Dao
interface ValoracionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertaUna(item:Valoracion)

    @Update
    fun actualizaValoracion(item:Valoracion)

    @Query("SELECT * FROM Valoraciones WHERE idReceta= :buscar")
    fun obtenerPorNombreReceta(buscar:String):MutableList<Valoracion>

    @Query("SELECT * FROM Valoraciones")
    fun obtenerTodas():MutableList<Valoracion>

    @Query("SELECT * FROM Valoraciones WHERE idusuario= :buscar and idReceta= :idreceta")
    fun obtenerPorNombreUsuario(buscar:String, idreceta:String):MutableList<Valoracion>
}