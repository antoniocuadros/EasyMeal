package com.acl.easymeal.adapters

import android.content.Context
import android.graphics.Bitmap
import android.media.Image
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
/*
    La clase SliderPasosAdapter representa el adaptador que nos permite inicializar una lista de pasos en un
    Pager2ViewHolder que nos permitirá ver los pasos en un slider de cartas.
    Para ello recibirá una lista de pasos y otra de imágenes de los pasos y convetirá el texto e imagne en un elemento
    del Pager2ViewHolder definido por el layout paso_item que mostrará el nombre del paso y una imagen si
    este paso cuenta con imagen. Además se añade un botón en cada paso para reproducir audio y poder escucharlo.
 */
class SliderPasosAdapter(var pasos:MutableList<String>, var imgPasos:MutableList<Bitmap?>, context: Context, reproductor:TextToSpeech): RecyclerView.Adapter<SliderPasosAdapter.Pager2ViewHolder>(){
    var context = context
    var reproductor = reproductor

    inner class Pager2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val texto_num_paso = itemView.findViewById<TextView>(R.id.texto_num_paso)
        val descripcion_paso = itemView.findViewById<TextView>(R.id.descripcion_paso)
        val boton_reproducir = itemView.findViewById<ImageButton>(R.id.boton_reproducir)
        val imagen = itemView.findViewById<ImageView>(R.id.imagen_paso)

        init{
            boton_reproducir.setOnClickListener {
                reproductor.speak(pasos[adapterPosition], TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }
    }

    /*
        Este método se encarga de inflar la vista de acuerdo a un determinado layout, en este caso
        de acuerdo al layout paso_item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderPasosAdapter.Pager2ViewHolder {
        return Pager2ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.paso_item, parent, false)
        )
    }

    /*
        Este método obtiene el número total de elementos que contendrá el Pager2ViewHolder.
     */
    override fun getItemCount(): Int {
        return pasos.size
    }

    /*
        En este método se establece el contenido de cada item de la vista, para ello se asigna
        el nombre y la imagen del paso en caso de que haya una definida.
     */
    override fun onBindViewHolder(holder: SliderPasosAdapter.Pager2ViewHolder, position: Int) {
        holder.descripcion_paso.text = pasos[position]
        holder.texto_num_paso.text = (position + 1).toString()

        if(imgPasos[position] != null){
            holder.imagen.setImageBitmap(imgPasos[position])
        }

        holder.setIsRecyclable(false)
    }

    /*
        Se ha sobrecargado este método para poder parar el reproductor al pasar al elemento
        anterior o siguiente.
     */
    override fun onViewRecycled(holder: Pager2ViewHolder) {
        super.onViewRecycled(holder)
        reproductor.shutdown()
    }
}