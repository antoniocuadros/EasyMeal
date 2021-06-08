package com.acl.easymeal.modelo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/*
    Esta interfaz representa el DAO de la clase Usuario, es decir, a través de los métodos de esta
    interfaz accederemos a los datos de la tabla Usuarios de la base de datos.
 */
@Dao
interface UsuarioDao {

    /*
        Este método inserta un usuario.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertaUna(item:Usuario)

    /*
        Este método inserta una lista de usuarios.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertaLista(lista:MutableList<Usuario>)

    /*
        Este método obtiene todos los usuarios.
     */
    @Query("SELECT * FROM Usuarios")
    fun obtenerTodas():MutableList<Usuario>

    /*
        Este método obtiene todos los usuarios.
     */
    @Query("SELECT * FROM Usuarios WHERE username= :buscar")
    fun obtenerPorNombre(buscar:String):MutableList<Usuario>

    /*
        Este método obtiene el número de usuarios.
     */
    @Query("SELECT count(*) FROM Usuarios")
    fun obtenerNumUsuarios():Int

}