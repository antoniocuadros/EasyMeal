package com.acl.easymeal.modelo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UsuarioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertaUna(item:Usuario)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertaLista(lista:MutableList<Usuario>)

    @Query("SELECT * FROM Usuarios")
    fun obtenerTodas():MutableList<Usuario>

    @Query("SELECT * FROM Usuarios WHERE username= :buscar")
    fun obtenerPorNombre(buscar:String):MutableList<Usuario>

}