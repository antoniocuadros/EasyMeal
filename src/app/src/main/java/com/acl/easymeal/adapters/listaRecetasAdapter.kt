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

/*
    La clase listaRecetasAdapter representa el adaptador que nos permite inicializar una lista de recetas en un
    GridView. Para ello recibirá una lista de recetas y transformará cada elemento en un elemento
    del GridView definido por el layout receta_item que mostrará el nombre de la receta y una imagen de la misma.
 */
class listaRecetasAdapter(var listaRecetas:MutableList<Receta>, var context: Context, modo:String, activity: MainActivity): BaseAdapter(){
    private var modo = modo
    private var activity = activity

    /*
        En este método inflamos la vista del gridView, para ello se realizan los siguientes pasos:
            -> 1) Se obtiene la vista.
            -> 2) Se obtiene la receta de la posición actual.
            -> 3) Se obtienen los elementos del layout que se van a rellenar, como son la imagen y el texto.
            -> 4) Se asignan a los elementos anteriores los valores de la categoría actúal.
                  Adicionalmente se obtienen los botones de edicion y borrado. Si venimos de la pestaña perfil
                  se muestran los iconos para editar y borrar recetas ya que solo se mostrarán las nuestras, en
                  caso contrario no se muestran.
            -> 5) Se devuelve la vista.
    */
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

    /*
        Este método devuelve un elemento de la lista de recetas.
     */
    override fun getItem(position: Int): Any {
        return listaRecetas[position]
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
        return listaRecetas.size
    }
}