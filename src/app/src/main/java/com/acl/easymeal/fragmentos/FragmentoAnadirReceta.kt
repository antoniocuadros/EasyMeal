package com.acl.easymeal.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.cardview.widget.CardView
import com.acl.easymeal.R
import com.acl.easymeal.modelo.obtenerBaseDatos
import com.google.android.material.button.MaterialButton

class FragmentoAnadirReceta : Fragment() {
    private lateinit var boton_seleccion_imagen:CardView
    private lateinit var imagen_principal:ImageView
    private lateinit var input_titulo_receta:EditText
    private lateinit var spiner_categoria: Spinner
    private lateinit var ingrediente1:EditText
    private lateinit var cantidad_ingrediente1:EditText
    private lateinit var layout_ingrediente_1:LinearLayout
    private lateinit var ingrediente2:EditText
    private lateinit var cantidad_ingrediente2:EditText
    private lateinit var layout_ingrediente_2:LinearLayout
    private lateinit var ingrediente3:EditText
    private lateinit var cantidad_ingrediente3:EditText
    private lateinit var layout_ingrediente_3:LinearLayout
    private lateinit var ingrediente4:EditText
    private lateinit var cantidad_ingrediente4:EditText
    private lateinit var layout_ingrediente_4:LinearLayout
    private lateinit var ingrediente5:EditText
    private lateinit var cantidad_ingrediente5:EditText
    private lateinit var layout_ingrediente_5:LinearLayout
    private lateinit var ingrediente6:EditText
    private lateinit var cantidad_ingrediente6:EditText
    private lateinit var layout_ingrediente_6:LinearLayout
    private lateinit var anadiringrediente:CardView
    private lateinit var paso_1:EditText
    private lateinit var paso_2:EditText
    private lateinit var paso_3:EditText
    private lateinit var paso_4:EditText
    private lateinit var paso_5:EditText
    private lateinit var paso_6:EditText
    private lateinit var paso_7:EditText
    private lateinit var paso_8:EditText
    private lateinit var paso_9:EditText
    private lateinit var paso_10:EditText
    private lateinit var duracion:EditText
    private lateinit var anadirpaso:CardView
    private lateinit var boton_anadir:MaterialButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragmento_anadir_receta, container, false)

        vinculaVistasVariables(view)

        inicializaCategorías()

        estableceComportamientoBotonAnadirIngrediente()

        return view
    }

    private fun inicializaCategorías(){
        var db = obtenerBaseDatos(requireContext())
        var categorias = db.categoriaDao.obtenerTodas()
        var categorias_texto = mutableListOf<String>()

        for(categoria in categorias){
            categorias_texto.add(categoria.nombreCategoria)
        }

        val adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, categorias_texto)
        spiner_categoria.setAdapter(adapter)
    }

    /*
        Este método se encarga de dar la posibilidad de añadir un nuevo ingrediente para la receta
        haciendo visible un input para el nombre del ingrediente y otro input para la cantidad.
     */
    private fun estableceComportamientoBotonAnadirIngrediente(){
        anadiringrediente.setOnClickListener {
            //En primer lugar vemos cual es el siguiente
            var anadir = primerIngredienteVacio()
            if(anadir != 0){
                when(anadir){
                    2->{
                        layout_ingrediente_2.visibility = View.VISIBLE
                    }
                    3->{
                        layout_ingrediente_3.visibility = View.VISIBLE
                    }
                    4->{
                        layout_ingrediente_4.visibility = View.VISIBLE
                    }
                    5->{
                        layout_ingrediente_5.visibility = View.VISIBLE
                    }
                    6->{
                        layout_ingrediente_6.visibility = View.VISIBLE
                    }
                }
            }
            else{
                Toast.makeText(context, "Ha dejado un ingrediente vacío", Toast.LENGTH_LONG).show()
            }

        }
    }

    /*
        Este método se llamará al intentar añadir un nuevo ingrediente. En primer lugar se comprueba
        que el usuario no haya dejado en medio ninguno en blanco. Si no ha dejado ninguno vacío se
        devuelve el índice del que hay que añadir, en caso contrario se devuelve 0.
     */
    private fun primerIngredienteVacio():Int{
        var vacios:ArrayList<Int> = arrayListOf()
        var anadir = true
        if(ingrediente1.text.toString() == ""){
            vacios.add(1)
            anadir = false
        }
        if(ingrediente2.text.toString() == "") vacios.add(2)
        if(ingrediente3.text.toString() == "") vacios.add(3)
        if(ingrediente4.text.toString() == "") vacios.add(4)
        if(ingrediente5.text.toString() == "") vacios.add(5)
        if(ingrediente6.text.toString() == "") vacios.add(6)

        if(vacios.size == 0 ||  vacios.size == 1){
            anadiringrediente.visibility = View.GONE
        }
        else{
            anadiringrediente.visibility = View.VISIBLE
        }

        if(anadir && vacios.size > 0){
            return vacios[0]
        }
        else{
            return 0
        }
    }

    /*
        Este método se encarga de vincular cada atributo de la clase con su correspondiente
        elemento del layout.
     */
    private fun vinculaVistasVariables(view:View){
        boton_seleccion_imagen = view.findViewById(R.id.boton_seleccion_imagen)
        imagen_principal = view.findViewById(R.id.imagen_principal)
        input_titulo_receta = view.findViewById(R.id.input_titulo_receta)
        spiner_categoria = view.findViewById(R.id.spiner_categoria)
        ingrediente1 = view.findViewById(R.id.ingrediente1)
        cantidad_ingrediente1 = view.findViewById(R.id.cantidad_ingrediente1)
        layout_ingrediente_1 = view.findViewById(R.id.layout_ingrediente_1)
        ingrediente2 = view.findViewById(R.id.ingrediente2)
        cantidad_ingrediente2 = view.findViewById(R.id.cantidad_ingrediente2)
        layout_ingrediente_2 = view.findViewById(R.id.layout_ingrediente_2)
        ingrediente3 = view.findViewById(R.id.ingrediente3)
        cantidad_ingrediente3 = view.findViewById(R.id.cantidad_ingrediente3)
        layout_ingrediente_3 = view.findViewById(R.id.layout_ingrediente_3)
        ingrediente4 = view.findViewById(R.id.ingrediente4)
        cantidad_ingrediente4 = view.findViewById(R.id.cantidad_ingrediente4)
        layout_ingrediente_4 = view.findViewById(R.id.layout_ingrediente_4)
        ingrediente5 = view.findViewById(R.id.ingrediente5)
        cantidad_ingrediente5 = view.findViewById(R.id.cantidad_ingrediente5)
        layout_ingrediente_5 = view.findViewById(R.id.layout_ingrediente_5)
        ingrediente6 = view.findViewById(R.id.ingrediente6)
        cantidad_ingrediente6 = view.findViewById(R.id.cantidad_ingrediente6)
        layout_ingrediente_6 = view.findViewById(R.id.layout_ingrediente_6)
        anadiringrediente = view.findViewById(R.id.anadiringrediente)
        paso_1 = view.findViewById(R.id.paso_1)
        paso_2 = view.findViewById(R.id.paso_2)
        paso_3 = view.findViewById(R.id.paso_3)
        paso_4 = view.findViewById(R.id.paso_4)
        paso_5 = view.findViewById(R.id.paso_5)
        paso_6 = view.findViewById(R.id.paso_6)
        paso_7 = view.findViewById(R.id.paso_7)
        paso_8 = view.findViewById(R.id.paso_8)
        paso_9 = view.findViewById(R.id.paso_9)
        paso_10 = view.findViewById(R.id.paso_10)
        duracion = view.findViewById(R.id.duracion)
        anadirpaso = view.findViewById(R.id.anadirpaso)
        boton_anadir = view.findViewById(R.id.boton_anadir)
    }

}