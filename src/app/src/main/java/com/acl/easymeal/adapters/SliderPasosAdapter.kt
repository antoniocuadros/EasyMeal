package com.acl.easymeal.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.acl.easymeal.R
import com.acl.easymeal.modelo.Ingrediente

class SliderPasosAdapter(var pasos:MutableList<String>, context: Context): RecyclerView.Adapter<SliderPasosAdapter.Pager2ViewHolder>() {
    var context = context


    inner class Pager2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val texto_num_paso = itemView.findViewById<TextView>(R.id.texto_num_paso)
        val descripcion_paso = itemView.findViewById<TextView>(R.id.descripcion_paso)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderPasosAdapter.Pager2ViewHolder {
        return Pager2ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.paso_item, parent, false)
        )
    }


    override fun getItemCount(): Int {
        return pasos.size
    }


    override fun onBindViewHolder(holder: SliderPasosAdapter.Pager2ViewHolder, position: Int) {
        holder.descripcion_paso.text = pasos[position]
        holder.texto_num_paso.text = (position + 1).toString()
    }
}