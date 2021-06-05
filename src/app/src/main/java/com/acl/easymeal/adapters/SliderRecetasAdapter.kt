package com.acl.easymeal.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.acl.easymeal.MainActivity
import com.acl.easymeal.R
import com.acl.easymeal.modelo.Receta
import com.acl.easymeal.modelo.obtenerBaseDatos
import me.relex.circleindicator.CircleIndicator3

class SliderRecetasAdapter(var recetas:MutableList<Receta>,context: Context, activity: MainActivity, modo:Int): RecyclerView.Adapter<SliderRecetasAdapter.Pager2ViewHolder>() {
    var context = context
    var activity = activity
    var modo = modo


    inner class Pager2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagen_receta_item = itemView.findViewById<ImageView>(R.id.imagen_receta_item)
        val nombre_receta_item =  itemView.findViewById<TextView>(R.id.nombre_receta_item)
        val icono_borrar = itemView.findViewById<CardView>(R.id.icono_borrar)
        val icono_editar = itemView.findViewById<CardView>(R.id.icono_editar)

        init {
            icono_borrar.setOnClickListener {
                var db = obtenerBaseDatos(context)
                db.recetaDao.elimina(recetas[adapterPosition])

                recetas.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
                notifyItemRangeChanged(adapterPosition, recetas.size)
                notifyDataSetChanged()
                var indicador_num = it.rootView.findViewById<CircleIndicator3>(R.id.indicador_slider_mis_recetas)
                indicador_num.createIndicators(recetas.size, adapterPosition+1)
            }

            icono_editar.setOnClickListener {
                activity.fromPerfilToEditar(recetas[adapterPosition].id.toString())
            }

            itemView.setOnClickListener {
                activity.fromPerfilToMostrarReceta(recetas[adapterPosition].id.toString())
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderRecetasAdapter.Pager2ViewHolder {
        return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.receta_item, parent, false))
    }


    override fun getItemCount(): Int {
        return recetas.size
    }


    override fun onBindViewHolder(holder: SliderRecetasAdapter.Pager2ViewHolder, position: Int) {
        holder.imagen_receta_item.setImageBitmap(recetas[position].imagen)
        holder.nombre_receta_item.text = recetas[position].nombreReceta
        if(modo == 0){
            holder.icono_borrar.visibility = View.GONE
        }
        else{
            holder.icono_borrar.visibility = View.VISIBLE
        }
    }
}