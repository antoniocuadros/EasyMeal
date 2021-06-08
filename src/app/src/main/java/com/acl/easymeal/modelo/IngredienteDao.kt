package com.acl.easymeal.modelo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/*
    Esta interfaz representa el DAO de la clase Ingrediente, es decir, a través de los métodos de esta
    interfaz accederemos a los datos de la tabla Ingredientes de la base de datos.
 */
@Dao
interface IngredienteDao {

    /*
        Este método inserta un ingrediente.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertaUna(item:Ingrediente)

    /*
        Este método inserta una lista de ingredientes.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertaLista(lista:MutableList<Ingrediente>)

    /*
        Este método obtiene todos los ingredientes.
     */
    @Query("SELECT * FROM Ingredientes")
    fun obtenerTodas():MutableList<Ingrediente>

    /*
        Este método obtiene un ingrediente por nombre
     */
    @Query("SELECT * FROM Ingredientes WHERE nombreIngrediente= :buscar")
    fun obtenerPorNombre(buscar:String):MutableList<Ingrediente>
}