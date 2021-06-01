package com.acl.easymeal

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.acl.easymeal.modelo.Categoria

class CategoriaAdapter(var listaCategorias:MutableList<Categoria>, context: Context): BaseAdapter(){
    var context = context

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        // Paso 1)
        val vista: View = View.inflate(parent?.context, R.layout.categoria_item, null)

        // Paso 2)
        val categoria = this.listaCategorias[position]

        //Paso 3)
        val imagen_categoria_palabra = vista.findViewById<ImageView>(R.id.imagen_nivel_palabra)
        val texto_categoria_palabra = vista.findViewById<TextView>(R.id.texto_categoria)

        // Paso 4)
        val conversor:ConversorImagen = ConversorImagen()
        imagen_categoria_palabra.setImageBitmap(categoria.imagen)
        texto_categoria_palabra.text = categoria.nombreCategoria



        //Paso 5)
        return vista
    }


    override fun getItem(position: Int): Any {
        return listaCategorias[position]
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getCount(): Int {
        return listaCategorias.size
    }
}