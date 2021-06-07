package com.acl.easymeal.adapters

import android.content.Context
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.acl.easymeal.R
import com.acl.easymeal.modelo.Ingrediente
import java.util.*

class SliderPasosAdapter(var pasos:MutableList<String>, context: Context, reproductor:TextToSpeech): RecyclerView.Adapter<SliderPasosAdapter.Pager2ViewHolder>(){
    var context = context
    var reproductor = reproductor

    inner class Pager2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val texto_num_paso = itemView.findViewById<TextView>(R.id.texto_num_paso)
        val descripcion_paso = itemView.findViewById<TextView>(R.id.descripcion_paso)
        val boton_reproducir = itemView.findViewById<ImageButton>(R.id.boton_reproducir)

        init{
            boton_reproducir.setOnClickListener {
                reproductor.speak(pasos[adapterPosition], TextToSpeech.QUEUE_FLUSH, null, null)
            }

        }

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
        holder.setIsRecyclable(false)
    }

    override fun onViewRecycled(holder: Pager2ViewHolder) {
        super.onViewRecycled(holder)
        reproductor.shutdown()
    }
}