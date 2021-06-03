package com.acl.easymeal.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.acl.easymeal.R
import com.acl.easymeal.modelo.Receta

class SliderRecetasAdapter(var recetas:MutableList<Receta>,context: Context): RecyclerView.Adapter<SliderRecetasAdapter.Pager2ViewHolder>() {
    var context = context


    inner class Pager2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagen_receta_item = itemView.findViewById<ImageView>(R.id.imagen_receta_item)
        val nombre_receta_item =  itemView.findViewById<TextView>(R.id.nombre_receta_item)
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
    }
}