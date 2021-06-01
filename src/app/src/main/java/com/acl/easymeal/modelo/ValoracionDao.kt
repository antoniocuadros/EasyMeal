package com.acl.easymeal.modelo

import androidx.room.*

@Dao
interface ValoracionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertaUna(item:Valoracion)

    @Update
    fun actualizaValoracion(item:Valoracion)

    @Query("SELECT * FROM Valoraciones WHERE idReceta= :buscar")
    fun obtenerPorNombre(buscar:String):MutableList<Valoracion>

}