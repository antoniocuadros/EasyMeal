package com.acl.easymeal.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import com.acl.easymeal.MainActivity
import com.acl.easymeal.adapters.CategoriaAdapter
import com.acl.easymeal.R
import com.acl.easymeal.modelo.Categoria
import com.acl.easymeal.modelo.DataBaseRecetas
import com.acl.easymeal.modelo.obtenerBaseDatos

/*
    La clase FragmentoCategorias se encarga de dotar de funcionalidad al apartado de la aplicación
    encargado de mostrar un listado de categorías.
 */
/*
    Los atributos de esta clase son:
        -> adapter: Variable que representa el adaptador que nos permitirá pasar de una lista de categorías a una GridView de categorías, de tipo CategoriaAdapter.
        -> categorias: Lista de todas las categorías de recetas, de tipo MutableList<Categoria>
        -> cuadricula_categorias: Cuadrícula en la que se mostrarán todas las categorías, de tipo GridView.
 */
class FragmentoCategorias : Fragment() {
    private lateinit var adapter: CategoriaAdapter
    private lateinit var categorias:MutableList<Categoria>
    private lateinit var cuadricula_categorias: GridView

    /*
        En el método onCreate de un fragmento es llamado durante la creación del mismo.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    /*
        En este método se realizan los siguientes pasos:
            -> 1) Se obtiene la vista.
            -> 2) Se llama al método inicializaListaCategorías que inicializa el GridView de categorías.
            -> 3) Se devuelve la vista.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        //Paso 1
        val view = inflater.inflate(R.layout.fragmento_categorias, container, false)

        //Paso 2
        inicializaListaCategorías(view)

        //Paso 3
        return view
    }

    /*
        Este método se encarga de obtener todas las categorías de la base de datos para posteriormente
        inicializar el adapter pasándole dichas categorías así como inicializa el clickEvent sobre cada
        categoría para ir a la lista de recetas que pertenecen a dicha categoría.
     */
    private fun inicializaListaCategorías(view:View){
        cuadricula_categorias = view.findViewById<GridView>(R.id.cuadricula_categorias)
        val db: DataBaseRecetas = obtenerBaseDatos(requireContext())
        categorias = db.categoriaDao.obtenerTodas()

        adapter = CategoriaAdapter(categorias, requireContext())
        cuadricula_categorias.adapter = adapter

        cuadricula_categorias.setOnItemClickListener{cuadricula_categorias, _, i,_ ->
            var categoria = cuadricula_categorias.getItemAtPosition(i) as Categoria

            (activity as MainActivity).fromCategoriasToRecetas(categoria.nombreCategoria)
        }
    }
}