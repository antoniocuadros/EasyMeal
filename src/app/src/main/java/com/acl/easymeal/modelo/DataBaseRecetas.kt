package com.acl.easymeal.modelo

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.Toast
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.acl.easymeal.ConversorImagen
import com.acl.easymeal.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStream

@Database(entities = [Categoria::class, Ingrediente::class, Receta::class, Usuario::class, Valoracion::class], version = 1, exportSchema = false)
@TypeConverters(ConversorImagen::class)
abstract class DataBaseRecetas: RoomDatabase() {
    abstract val categoriaDao:CategoriaDao
    abstract val ingredienteDao:IngredienteDao
    abstract val recetaDao:RecetaDao
    abstract val usuarioDao:UsuarioDao
    abstract val valoracionDao:ValoracionDao
}

private lateinit var instancia_data_base:DataBaseRecetas

public fun obtenerBaseDatos(context: Context):DataBaseRecetas{
    if(!::instancia_data_base.isInitialized){
        instancia_data_base = Room.databaseBuilder(context.applicationContext,
                DataBaseRecetas::class.java, "database_recetas").allowMainThreadQueries().build()

        if(instancia_data_base.usuarioDao.obtenerNumUsuarios() == 0){

            inicializaAdmin(context)
            inicializaCategorias(context)
            inicializaIngredientes(context)
            inicializaRecetas(context)
        }

    }

    return instancia_data_base
}

private fun inicializaAdmin(context:Context){
    val drawable: Drawable? = context.getDrawable(R.drawable.user)

    val bitmap:Bitmap = (drawable as BitmapDrawable).bitmap

    instancia_data_base.usuarioDao.insertaUna(Usuario("admin", "admin", bitmap))
}

private fun inicializaRecetas(context:Context){
    var recetas:MutableList<Receta>
    var inputStream: InputStream = context.assets!!.open("recetas.json")
    val recetas_texto = inputStream.bufferedReader().use{it.readText()}
    val gson = Gson()
    val tipo_a_leer = object : TypeToken<MutableList<Receta>>() {}.type
    recetas = gson.fromJson<MutableList<Receta>>(recetas_texto, tipo_a_leer)

    //Añadimos las imágenes de String a bitArray
    val conversor:ConversorImagen = ConversorImagen()

    for(receta in recetas){
        val idImagen = conversor.stringToID(receta.imagen_text, context)
        val drawable: Drawable? = context.getDrawable(idImagen)

        val bitmap:Bitmap = (drawable as BitmapDrawable).bitmap

        receta.imagen = bitmap
    }

    instancia_data_base.recetaDao.insertaLista(recetas)
}

private fun inicializaCategorias(context:Context){
    var categorias:MutableList<Categoria>
    var inputStream: InputStream = context.assets!!.open("categorias.json")
    val categorias_texto = inputStream.bufferedReader().use{it.readText()}
    val gson = Gson()
    val tipo_a_leer = object : TypeToken<MutableList<Categoria>>() {}.type
    categorias = gson.fromJson<MutableList<Categoria>>(categorias_texto, tipo_a_leer)

    //Añadimos las imágenes de String a bitArray
    val conversor:ConversorImagen = ConversorImagen()

    for(categoria in categorias){
        val idImagen = conversor.stringToID(categoria.nombreImagen, context)
        val drawable: Drawable? = context.getDrawable(idImagen)

        val bitmap:Bitmap = (drawable as BitmapDrawable).bitmap

        categoria.imagen = bitmap
    }

    instancia_data_base.categoriaDao.insertaLista(categorias)
}

private fun inicializaIngredientes(context:Context){
    var ingredientes:MutableList<Ingrediente>
    var inputStream: InputStream = context.assets!!.open("ingredientes.json")
    val ingredientes_texto = inputStream.bufferedReader().use{it.readText()}
    val gson = Gson()
    val tipo_a_leer = object : TypeToken<MutableList<Ingrediente>>() {}.type
    ingredientes = gson.fromJson<MutableList<Ingrediente>>(ingredientes_texto, tipo_a_leer)


    instancia_data_base.ingredienteDao.insertaLista(ingredientes)
}