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

/*
    La clase SliderIngredienteAdapter representa el adaptador que nos permite inicializar una lista de recetas en un
    Pager2ViewHolder que nos permitirá ver los ingredientes en un slider de cartas.
    Para ello recibirá una lista de ingredientes y otra de cantidades y convetirá el texto en un elemento
    del Pager2ViewHolder definido por el layout ingrediente_item que mostrará el nombre del ingrediente, su cantidad
    y una imagen si este ingrediente se encuentra en la base de datos definido.
 */
class SliderIngredienteAdapter(var ingredientes:MutableList<Ingrediente>, var cantidades:MutableList<String>, context: Context): RecyclerView.Adapter<SliderIngredienteAdapter.Pager2ViewHolder>() {
    var context = context


    inner class Pager2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagen_ingrediente = itemView.findViewById<ImageView>(R.id.imagen_ingrediente)
        val nombre_ingrediente = itemView.findViewById<TextView>(R.id.nombre_ingrediente)
    }


    /*
        Este método se encarga de inflar la vista de acuerdo a un determinado layout, en este caso
        de acuerdo al layout ingrediente_item.
     */
    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): SliderIngredienteAdapter.Pager2ViewHolder {
        return Pager2ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.ingrediente_item, parent, false)
        )
    }

    /*
        Este método obtiene el número total de elementos que contendrá el Pager2ViewHolder.
     */
    override fun getItemCount(): Int {
        return ingredientes.size
    }

    /*
        En este método se establece el contenido de cada item de la vista, para ello se asigna
        el nombre y cantidad del ingrediente así como la imagen del mismo en caso de que haya
        una definida.
     */
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