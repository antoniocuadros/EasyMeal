package com.acl.easymeal.modelo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface IngredienteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertaUna(item:Ingrediente)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertaLista(lista:MutableList<Ingrediente>)

    @Query("SELECT * FROM Ingredientes")
    fun obtenerTodas():MutableList<Ingrediente>

    @Query("SELECT * FROM Ingredientes WHERE nombreIngrediente= :buscar")
    fun obtenerPorNombre(buscar:String):MutableList<Ingrediente>
}