package com.acl.easymeal.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.acl.easymeal.R
import com.acl.easymeal.modelo.Categoria

/*
    La clase CategoriaAdapter representa el adaptador que nos permite inicializar una lista de categorías en un
    GridView. Para ello recibirá una lista de categorías y transformará cada elemento en un elemento
    del GridView definido por el layout categoria_item que mostrará el nombre de la categoría así como
    una imagen.
 */
class CategoriaAdapter(var listaCategorias:MutableList<Categoria>, context: Context): BaseAdapter(){
    var context = context

    /*
        En este método inflamos la vista del gridView, para ello se realizan los siguientes pasos:
        -> 1) Se obtiene la vista.
        -> 2) Se obtiene la categoría de la posición actual.
        -> 3) Se obtienen los elementos del layout que se van a rellenar, como son la imagen y el texto.
        -> 4) Se asignan a los elementos anteriores los valores de la categoría actúal.
        -> 5) Se devuelve la vista.
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        // Paso 1)
        val vista: View = View.inflate(parent?.context, R.layout.categoria_item, null)

        // Paso 2)
        val categoria = this.listaCategorias[position]

        //Paso 3)
        val imagen_categoria_palabra = vista.findViewById<ImageView>(R.id.imagen_nivel_palabra)
        val texto_categoria_palabra = vista.findViewById<TextView>(R.id.texto_categoria)

        // Paso 4)
        imagen_categoria_palabra.setImageBitmap(categoria.imagen)
        texto_categoria_palabra.text = categoria.nombreCategoria

        //Paso 5)
        return vista
    }


    /*
        Este método devuelve un elemento de la lista de categorías.
     */
    override fun getItem(position: Int): Any {
        return listaCategorias[position]
    }

    /*
        Este método devuelve el id de un elemento de la lista. Este id será la posición en la
        que se encuentra en la lista.
     */
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    /*
        Este método devuelve el número total de elementos que se van a añadir a la vista.
     */
    override fun getCount(): Int {
        return listaCategorias.size
    }
}