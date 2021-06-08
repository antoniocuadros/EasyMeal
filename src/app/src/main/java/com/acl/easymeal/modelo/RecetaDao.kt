package com.acl.easymeal.modelo

import androidx.room.*

/*
    Esta interfaz representa el DAO de la clase Receta, es decir, a través de los métodos de esta
    interfaz accederemos a los datos de la tabla Recetas de la base de datos.
 */
@Dao
interface RecetaDao {
    /*
        Este método inserta una receta
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertaUna(item:Receta)

    /*
        Este método inserta una lista de recetas
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertaLista(lista:MutableList<Receta>)

    /*
        Este método obtiene todas las recetas
     */
    @Query("SELECT * FROM Recetas")
    fun obtenerTodas():MutableList<Receta>

    /*
        Este método obtiene una receta por id
     */
    @Query("SELECT * FROM Recetas WHERE id= :buscar")
    fun obtenerPorID(buscar:String):Receta

    /*
        Este método obtiene una receta por nombre
     */
    @Query("SELECT * FROM Recetas WHERE nombreReceta= :buscar")
    fun obtenerPorNombre(buscar:String):MutableList<Receta>

    /*
        Este método obtiene todas las recetas de una determinada categoría.
     */
    @Query("SELECT * FROM Recetas WHERE categoria= :buscar")
    fun obtenerPorCategoría(buscar:String):MutableList<Receta>

    /*
        Este método obtiene todas las recetas que tienen por duración un intervalo dado.
     */
    @Query("SELECT * FROM Recetas WHERE duracion >= :tmp1 and duracion <= :tmp2 ")
    fun obtenerPorTiempo(tmp1:Int, tmp2:Int):MutableList<Receta>

    /*
        Este método obtiene todas las recetas de un autor.
     */
    @Query("SELECT * FROM Recetas WHERE idAutor= :buscar")
    fun obtenerPorAutor(buscar:String):MutableList<Receta>

    /*
        Este método obtiene todas las recetas que tienen un determinado ingrediente.
     */
    @Query("SELECT * FROM Recetas WHERE ingrediente1 IN(:buscar) or ingrediente2 IN(:buscar) or ingrediente3 IN(:buscar) or ingrediente4 IN(:buscar) or ingrediente5 IN(:buscar) or ingrediente6 IN(:buscar)")
    fun obtenerPorIngrediente(buscar:List<String>):MutableList<Receta>

    /*
        Este método obtiene todas las recetas que contienen un ingrediente y están en un intervalo temporal de duración.
     */
    @Query("SELECT * FROM Recetas WHERE (ingrediente1 IN(:buscar) or ingrediente2 IN(:buscar) or ingrediente3 IN(:buscar) or ingrediente4 IN(:buscar) or ingrediente5 IN(:buscar) or ingrediente6 IN(:buscar)) and (duracion >= :tmp1 and duracion <= :tmp2)")
    fun obtenerPorIngredienteTiempo(buscar:List<String>, tmp1:Int, tmp2:Int):MutableList<Receta>

    /*
        Este método obtiene todas las recetas que contienen un ingrediente y tienen una dificultad determinada
     */
    @Query("SELECT * FROM Recetas WHERE (ingrediente1 IN(:buscar) or ingrediente2 IN(:buscar) or ingrediente3 IN(:buscar) or ingrediente4 IN(:buscar) or ingrediente5 IN(:buscar) or ingrediente6 IN(:buscar)) and (dificultad= :dif)")
    fun obtenerPorIngredienteDificultad(buscar:List<String>, dif:String):MutableList<Receta>

    /*
        Este método obtiene todas las recetas que contienen un ingrediente y una categoría concreta.
     */
    @Query("SELECT * FROM Recetas WHERE (ingrediente1 IN(:buscar) or ingrediente2 IN(:buscar) or ingrediente3 IN(:buscar) or ingrediente4 IN(:buscar) or ingrediente5 IN(:buscar) or ingrediente6 IN(:buscar)) and (categoria= :cat)")
    fun obtenerPorCategoríaIngrediente(buscar:List<String>, cat:String):MutableList<Receta>

    /*
        Este método obtiene todas las recetas de una dificultad.
     */
    @Query("SELECT * FROM Recetas WHERE (dificultad= :dif)")
    fun obtenerPorDificultad(dif:String):MutableList<Receta>

    /*
        Este método elimina una receta
     */
    @Delete
    fun elimina(receta:Receta)

    /*
        Este método actualiza una receta
     */
    @Update
    fun actualiza(receta:Receta)
}