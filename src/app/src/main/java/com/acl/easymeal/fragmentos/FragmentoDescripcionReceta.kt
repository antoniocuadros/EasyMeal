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
import com.acl.easymeal.adapters.SliderIngredienteAdapter
import com.acl.easymeal.adapters.SliderRecetasAdapter
import com.acl.easymeal.modelo.DataBaseRecetas
import com.acl.easymeal.modelo.Ingrediente
import com.acl.easymeal.modelo.Receta
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

        estableceIngredientes(receta, db)


    }


    /*
    Este método se encarga de inicializar el slider de ingredientes dada una determinada receta.
     */
    private fun estableceIngredientes(receta: Receta, db:DataBaseRecetas){
        //Slider Ingredientes
        var ingredientes = mutableListOf<Ingrediente>()
        var cantidades = mutableListOf<String>()

        if(receta.ingrediente1 != ""){
            ingredientes.add(db.ingredienteDao.obtenerPorNombre(receta.ingrediente1)[0])
            cantidades.add(receta.cantidad_ingrediente1)
        }
        if(receta.ingrediente2 != ""){
            ingredientes.add(db.ingredienteDao.obtenerPorNombre(receta.ingrediente2)[0])
            cantidades.add(receta.cantidad_ingrediente2)
        }
        if(receta.ingrediente3 != ""){
            ingredientes.add(db.ingredienteDao.obtenerPorNombre(receta.ingrediente3)[0])
            cantidades.add(receta.cantidad_ingrediente3)
        }
        if(receta.ingrediente4 != ""){
            ingredientes.add(db.ingredienteDao.obtenerPorNombre(receta.ingrediente4)[0])
            cantidades.add(receta.cantidad_ingrediente4)
        }
        if(receta.ingrediente5 != ""){
            ingredientes.add(db.ingredienteDao.obtenerPorNombre(receta.ingrediente5)[0])
            cantidades.add(receta.cantidad_ingrediente5)
        }
        if(receta.ingrediente6 != ""){
            ingredientes.add(db.ingredienteDao.obtenerPorNombre(receta.ingrediente6)[0])
            cantidades.add(receta.cantidad_ingrediente6)
        }
        if(receta.ingrediente7 != ""){
            ingredientes.add(db.ingredienteDao.obtenerPorNombre(receta.ingrediente7)[0])
            cantidades.add(receta.cantidad_ingrediente7)
        }
        if(receta.ingrediente8 != ""){
            ingredientes.add(db.ingredienteDao.obtenerPorNombre(receta.ingrediente8)[0])
            cantidades.add(receta.cantidad_ingrediente8)
        }
        if(receta.ingrediente9 != ""){
            ingredientes.add(db.ingredienteDao.obtenerPorNombre(receta.ingrediente9)[0])
            cantidades.add(receta.cantidad_ingrediente9)
        }
        if(receta.ingrediente10 != ""){
            ingredientes.add(db.ingredienteDao.obtenerPorNombre(receta.ingrediente10)[0])
            cantidades.add(receta.cantidad_ingrediente10)
        }
        if(receta.ingrediente11 != ""){
            ingredientes.add(db.ingredienteDao.obtenerPorNombre(receta.ingrediente11)[0])
            cantidades.add(receta.cantidad_ingrediente11)
        }
        if(receta.ingrediente12 != ""){
            ingredientes.add(db.ingredienteDao.obtenerPorNombre(receta.ingrediente12)[0])
            cantidades.add(receta.cantidad_ingrediente12)
        }

        slider_ingredientes_desc.adapter = SliderIngredienteAdapter(ingredientes, cantidades, requireContext())
        slider_ingredientes_desc.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        indicador_slider_ingredientes.setViewPager(slider_ingredientes_desc)
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