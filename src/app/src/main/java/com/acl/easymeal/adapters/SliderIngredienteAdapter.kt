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
import com.acl.easymeal.modelo.Receta

class SliderIngredienteAdapter(var ingredientes:MutableList<Ingrediente>, var cantidades:MutableList<String>, context: Context): RecyclerView.Adapter<SliderIngredienteAdapter.Pager2ViewHolder>() {
    var context = context


    inner class Pager2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagen_ingrediente = itemView.findViewById<ImageView>(R.id.imagen_ingrediente)
        val nombre_ingrediente = itemView.findViewById<TextView>(R.id.nombre_ingrediente)
    }


    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): SliderIngredienteAdapter.Pager2ViewHolder {
        return Pager2ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.ingrediente_item, parent, false)
        )
    }


    override fun getItemCount(): Int {
        return ingredientes.size
    }


    override fun onBindViewHolder(holder: SliderIngredienteAdapter.Pager2ViewHolder, position: Int) {
        if(ingredientes[position].urlImagen != "none"){

            var id_imagen = context.resources.getIdentifier(ingredientes[position].urlImagen, "drawable", "com.acl.easymeal")
            holder.imagen_ingrediente.setImageResource(id_imagen)
        }
        else{
            holder.imagen_ingrediente.visibility = View.GONE
        }

        var texto_ingrediente:String

        if(cantidades[position] != ""){
            texto_ingrediente = cantidades[position] + " de " + ingredientes[position].nombreIngrediente
        }
        else{
            texto_ingrediente = ingredientes[position].nombreIngrediente
        }
        holder.nombre_ingrediente.text = texto_ingrediente
    }
}