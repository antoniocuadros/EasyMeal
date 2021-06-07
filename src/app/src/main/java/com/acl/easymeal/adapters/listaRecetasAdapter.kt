package com.acl.easymeal.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.acl.easymeal.MainActivity
import com.acl.easymeal.R
import com.acl.easymeal.modelo.Receta
import com.acl.easymeal.modelo.obtenerBaseDatos

class listaRecetasAdapter(var listaRecetas:MutableList<Receta>, var context: Context, modo:String, activity: MainActivity): BaseAdapter(){
    private var modo = modo
    private var activity = activity

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        // Paso 1)
        val vista: View = View.inflate(parent?.context, R.layout.receta_item, null)

        // Paso 2)
        val receta = this.listaRecetas[position]

        //Paso 3)
        val imagen_receta_item = vista.findViewById<ImageView>(R.id.imagen_receta_item)
        val nombre_receta_item =  vista.findViewById<TextView>(R.id.nombre_receta_item)

        // Paso 4)
        imagen_receta_item.setImageBitmap(receta.imagen)
        nombre_receta_item.text = receta.nombreReceta

        var icono_borrar = vista.findViewById<CardView>(R.id.icono_borrar)
        var icono_editar = vista.findViewById<CardView>(R.id.icono_editar)

        if(modo != "perfil"){
            icono_borrar.visibility = View.GONE
            icono_editar.visibility = View.GONE
        }
        else{
            icono_borrar.setOnClickListener {
                var db = obtenerBaseDatos(context)
                db.recetaDao.elimina(this.listaRecetas[position])

                this.listaRecetas.removeAt(position)
                notifyDataSetChanged()
            }

            icono_editar.setOnClickListener {
                activity.fromPerfilToEditar(this.listaRecetas[position].id.toString())
            }
        }


        //Paso 5)
        return vista
    }


    override fun getItem(position: Int): Any {
        return listaRecetas[position]
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return listaRecetas.size
    }
}