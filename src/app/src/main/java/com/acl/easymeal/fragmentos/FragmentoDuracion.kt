package com.acl.easymeal.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import com.acl.easymeal.MainActivity
import com.acl.easymeal.R


class FragmentoDuracion : Fragment() {
    private lateinit var carta_prisa:CardView
    private lateinit var carta_no_prisa:CardView
    private lateinit var carta_fogones:CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragmento_duracion, container, false)

        vinculaVistas(view)

        gestionaClicksBotones()

        return view
    }



    /*
        Este método se encarga de gestionar las pulsaciones en los botones para redirigir a la pestaña
        de lista de recetas.
     */
    private fun gestionaClicksBotones(){
        carta_prisa.setOnClickListener {
            (activity as MainActivity).fromDuracionToRecetas("15")
        }
        carta_no_prisa.setOnClickListener {
            (activity as MainActivity).fromDuracionToRecetas("60")
        }
        carta_fogones.setOnClickListener {
            (activity as MainActivity).fromDuracionToRecetas("300")
        }
    }
    /*
        Este método se encarga de vincular cada atributo de la clase de tipo vista con su correspondiente
        elemento del layout.
     */
    private fun vinculaVistas(view:View){
        carta_prisa = view.findViewById(R.id.carta_prisa)
        carta_no_prisa = view.findViewById(R.id.carta_no_prisa)
        carta_fogones = view.findViewById(R.id.carta_fogones)
    }
}