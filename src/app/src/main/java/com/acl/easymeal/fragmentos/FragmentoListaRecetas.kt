package com.acl.easymeal.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.acl.easymeal.R
import com.acl.easymeal.adapters.listaRecetasAdapter
import com.acl.easymeal.modelo.Receta
import com.acl.easymeal.modelo.obtenerBaseDatos


class FragmentoListaRecetas : Fragment(), androidx.appcompat.widget.SearchView.OnQueryTextListener {
    lateinit var adapter:listaRecetasAdapter
    lateinit var recetas:MutableList<Receta>
    lateinit var cuadricula_recetas: GridView
    private val argumentos:FragmentoListaRecetasArgs by navArgs()
    private lateinit var barra_busqueda:androidx.appcompat.widget.SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragmento_lista_recetas, container, false)

        vinculaVistasAtributos(view)

        inicializaListaRecetas()

        barra_busqueda.setOnQueryTextListener(this)

        return view
    }

    /*
        Este método se encarga de vincular cada atributo de la clase de tipo vista con su elemento
        correspondiente en el layout.
     */
    private fun vinculaVistasAtributos(view:View){
        cuadricula_recetas = view.findViewById(R.id.cuadricula_recetas)
        barra_busqueda = view.findViewById(R.id.barra_busqueda)
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
            if(argumentos.Tiempo != null){
                when(argumentos.Tiempo){
                    "15"->{
                        recetas = db.recetaDao.obtenerPorTiempo(0, 15)
                    }
                    "60"->{
                        recetas = db.recetaDao.obtenerPorTiempo(16, 60)
                    }
                    "300"->{
                        recetas = db.recetaDao.obtenerPorTiempo(61, 300)
                    }
                }
            }
            else{
                recetas = db.recetaDao.obtenerTodas()
            }
        }


        adapter = listaRecetasAdapter(recetas, requireContext())
        cuadricula_recetas.adapter = adapter
    }

    override fun onQueryTextSubmit(query: String?): Boolean {

        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        var db = obtenerBaseDatos(requireContext())
        var ingredientes = newText?.split(' ')
        var recetas = db.recetaDao.obtenerPorIngrediente(ingredientes!!)
        adapter = listaRecetasAdapter(recetas, requireContext())
        cuadricula_recetas.adapter = adapter
        return false
    }
}