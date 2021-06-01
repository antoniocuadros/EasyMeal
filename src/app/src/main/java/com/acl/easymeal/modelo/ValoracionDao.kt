package com.acl.easymeal.modelo

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

interface ValoracionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertaUna(item:Valoracion)

    @Update
    fun actualizaValoracion(item:Valoracion)

    @Query("SELECT * FROM Valoraciones WHERE idReceta= :buscar")
    fun obtenerPorNombre(buscar:String):MutableList<Valoracion>
    
}