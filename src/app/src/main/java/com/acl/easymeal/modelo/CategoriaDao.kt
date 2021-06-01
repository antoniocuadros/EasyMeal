package com.acl.easymeal.modelo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CategoriaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertaUna(item:Categoria)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertaLista(lista:MutableList<Categoria>)

    @Query("SELECT * FROM Categorias")
    fun obtenerTodas():MutableList<Categoria>

    @Query("SELECT * FROM Categorias WHERE nombreCategoria= :buscar")
    fun obtenerPorNombre(buscar:String):MutableList<Categoria>
}