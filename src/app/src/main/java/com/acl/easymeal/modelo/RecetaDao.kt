package com.acl.easymeal.modelo

import androidx.room.*

@Dao
interface RecetaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertaUna(item:Receta)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertaLista(lista:MutableList<Receta>)

    @Query("SELECT * FROM Recetas")
    fun obtenerTodas():MutableList<Receta>

    @Query("SELECT * FROM Recetas WHERE id= :buscar")
    fun obtenerPorID(buscar:String):Receta

    @Query("SELECT * FROM Recetas WHERE nombreReceta= :buscar")
    fun obtenerPorNombre(buscar:String):MutableList<Receta>

    @Query("SELECT * FROM Recetas WHERE categoria= :buscar")
    fun obtenerPorCategoría(buscar:String):MutableList<Receta>

    @Query("SELECT * FROM Recetas WHERE duracion >= :tmp1 and duracion <= :tmp2 ")
    fun obtenerPorTiempo(tmp1:Int, tmp2:Int):MutableList<Receta>

    @Query("SELECT * FROM Recetas WHERE idAutor= :buscar")
    fun obtenerPorAutor(buscar:String):MutableList<Receta>

    @Query("SELECT * FROM Recetas WHERE ingrediente1 IN(:buscar) or ingrediente2 IN(:buscar) or ingrediente3 IN(:buscar) or ingrediente4 IN(:buscar) or ingrediente5 IN(:buscar) or ingrediente6 IN(:buscar)")
    fun obtenerPorIngrediente(buscar:List<String>):MutableList<Receta>

    @Delete
    fun elimina(receta:Receta)

    @Update
    fun actualiza(receta:Receta)
}