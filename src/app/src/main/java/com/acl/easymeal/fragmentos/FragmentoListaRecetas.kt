package com.acl.easymeal.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.acl.easymeal.R
import com.acl.easymeal.adapters.listaRecetasAdapter
import com.acl.easymeal.modelo.Receta
import com.acl.easymeal.modelo.obtenerBaseDatos


class FragmentoListaRecetas : Fragment() {
    lateinit var adapter:listaRecetasAdapter
    lateinit var recetas:MutableList<Receta>
    lateinit var cuadricula_recetas: GridView
    private val argumentos:FragmentoListaRecetasArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragmento_lista_recetas, container, false)

        vinculaVistasAtributos(view)

        inicializaListaRecetas()

        return view
    }

    /*
        Este método se encarga de vincular cada atributo de la clase de tipo vista con su elemento
        correspondiente en el layout.
     */
    private fun vinculaVistasAtributos(view:View){
        cuadricula_recetas = view.findViewById(R.id.cuadricula_recetas)
    }

    /*
        Este método se encarga de obtener las recetas de la base de  datos e inicializa el adapter
        para mostrar la lista de recetas.
     */
    private fun inicializaListaRecetas(){
        var db = obtenerBaseDatos(requireContext())

        if(argumentos.Categoria != null){
            recetas = db.recetaDao.obtenerPorCategoría(argumentos.Categoria.toString())
        }
        else{
            recetas = db.recetaDao.obtenerTodas()
        }


        adapter = listaRecetasAdapter(recetas, requireContext())
        cuadricula_recetas.adapter = adapter
    }
}