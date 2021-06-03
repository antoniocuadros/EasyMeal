package com.acl.easymeal.modelo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecetaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertaUna(item:Receta)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertaLista(lista:MutableList<Receta>)

    @Query("SELECT * FROM Recetas")
    fun obtenerTodas():MutableList<Receta>

    @Query("SELECT * FROM Recetas WHERE nombreReceta= :buscar")
    fun obtenerPorNombre(buscar:String):MutableList<Receta>

    @Query("SELECT * FROM Recetas WHERE categoria= :buscar")
    fun obtenerPorCategoría(buscar:String):MutableList<Receta>

    @Query("SELECT * FROM Recetas WHERE duracion >= :tmp1 and duracion <= :tmp2 ")
    fun obtenerPorTiempo(tmp1:Int, tmp2:Int):MutableList<Receta>

    @Query("SELECT * FROM Recetas WHERE idAutor= :buscar")
    fun obtenerPorAutor(buscar:String):MutableList<Receta>
}