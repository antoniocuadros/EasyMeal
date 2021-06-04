package com.acl.easymeal.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.acl.easymeal.R
import com.acl.easymeal.modelo.obtenerBaseDatos
import me.relex.circleindicator.CircleIndicator3

class FragmentoDescripcionReceta : Fragment() {
    private lateinit var imagen_receta_desc:ImageView
    private lateinit var titulo_receta_desc:TextView
    private lateinit var descripcionReceta:TextView
    private lateinit var slider_ingredientes_desc: ViewPager2
    private lateinit var indicador_slider_ingredientes:CircleIndicator3
    private lateinit var slider_pasos_desc:ViewPager2
    private lateinit var indicador_slider_pasos:CircleIndicator3
    private lateinit var nombre_autor:TextView
    private val argumentos: FragmentoDescripcionRecetaArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragmento_descripcion_receta, container, false)

        vinculaVistas(view)

        inicializaContenidoReceta()

        return view
    }

    /*
        Este método se encargará de rellenar de contenido la vista dada una determinada receta.
     */
    private fun inicializaContenidoReceta(){
        var id_receta = argumentos.receta
        var db = obtenerBaseDatos(requireContext())
        var receta = db.recetaDao.obtenerPorID(id_receta.toString())

        titulo_receta_desc.text = receta.nombreReceta
        imagen_receta_desc.setImageBitmap(receta.imagen)
        descripcionReceta.text = receta.descripcion
        nombre_autor.text = receta.idAutor
    }

    /*
        Este método se encarga de vincular cada atributo de la clase de tipo vista con su correspondiente
        elemento del layout.
     */
    private fun vinculaVistas(view:View){
        imagen_receta_desc = view.findViewById(R.id.imagen_receta_desc)
        titulo_receta_desc = view.findViewById(R.id.titulo_receta_desc)
        descripcionReceta = view.findViewById(R.id.descripcionReceta)
        slider_ingredientes_desc = view.findViewById(R.id.slider_ingredientes_desc)
        indicador_slider_ingredientes = view.findViewById(R.id.indicador_slider_ingredientes)
        slider_pasos_desc = view.findViewById(R.id.slider_pasos_desc)
        indicador_slider_pasos = view.findViewById(R.id.indicador_slider_pasos)
        nombre_autor = view.findViewById(R.id.nombre_autor)
    }

}