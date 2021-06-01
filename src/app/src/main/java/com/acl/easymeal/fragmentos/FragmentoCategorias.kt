package com.acl.easymeal.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import com.acl.easymeal.adapters.CategoriaAdapter
import com.acl.easymeal.R
import com.acl.easymeal.modelo.Categoria
import com.acl.easymeal.modelo.DataBaseRecetas
import com.acl.easymeal.modelo.obtenerBaseDatos


class FragmentoCategorias : Fragment() {
    private lateinit var adapter: CategoriaAdapter
    private lateinit var categorias:MutableList<Categoria>
    private lateinit var cuadricula_categorias: GridView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragmento_categorias, container, false)

        inicializaListaCategorías(view)

        return view
    }

    private fun inicializaListaCategorías(view:View){
        cuadricula_categorias = view.findViewById<GridView>(R.id.cuadricula_categorias)
        val db: DataBaseRecetas = obtenerBaseDatos(requireContext())
        categorias = db.categoriaDao.obtenerTodas()

        adapter = CategoriaAdapter(categorias, requireContext())
        cuadricula_categorias.adapter = adapter
    }
}