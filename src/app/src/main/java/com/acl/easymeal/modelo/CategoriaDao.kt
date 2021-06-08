package com.acl.easymeal.modelo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/*
    Esta interfaz representa el DAO de la clase Categoría, es decir, a través de los métodos de esta
    interfaz accederemos a los datos de la tabla Categorías de la base de datos.
 */
@Dao
interface CategoriaDao {

    /*
        Este método inserta una categoría.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertaUna(item:Categoria)

    /*
        Este método inserta una lista de categorías.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertaLista(lista:MutableList<Categoria>)

    /*
        Este método obtiene todas las categorías.
     */
    @Query("SELECT * FROM Categorias")
    fun obtenerTodas():MutableList<Categoria>

    /*
        Este método obtiene una categoría por nombre
     */
    @Query("SELECT * FROM Categorias WHERE nombreCategoria= :buscar")
    fun obtenerPorNombre(buscar:String):MutableList<Categoria>
}