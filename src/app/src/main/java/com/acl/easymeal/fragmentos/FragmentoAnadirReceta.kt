package com.acl.easymeal.fragmentos

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.navArgs
import com.acl.easymeal.MainActivity
import com.acl.easymeal.R
import com.acl.easymeal.modelo.Receta
import com.acl.easymeal.modelo.Usuario
import com.acl.easymeal.modelo.obtenerBaseDatos
import com.google.android.material.button.MaterialButton
import java.io.ByteArrayOutputStream
import java.net.URI

@Suppress("DEPRECATION")
class FragmentoAnadirReceta : Fragment() {
    private lateinit var boton_seleccion_imagen:CardView
    private lateinit var imagen_principal:ImageView
    private lateinit var input_titulo_receta:EditText
    private lateinit var input_descripcion:EditText
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
    private lateinit var ingrediente7:EditText
    private lateinit var cantidad_ingrediente7:EditText
    private lateinit var layout_ingrediente_7:LinearLayout
    private lateinit var ingrediente8:EditText
    private lateinit var cantidad_ingrediente8:EditText
    private lateinit var layout_ingrediente_8:LinearLayout
    private lateinit var ingrediente9:EditText
    private lateinit var cantidad_ingrediente9:EditText
    private lateinit var layout_ingrediente_9:LinearLayout
    private lateinit var ingrediente10:EditText
    private lateinit var cantidad_ingrediente10:EditText
    private lateinit var layout_ingrediente_10:LinearLayout
    private lateinit var ingrediente11:EditText
    private lateinit var cantidad_ingrediente11:EditText
    private lateinit var layout_ingrediente_11:LinearLayout
    private lateinit var ingrediente12:EditText
    private lateinit var cantidad_ingrediente12:EditText
    private lateinit var layout_ingrediente_12:LinearLayout
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
    private lateinit var paso_11:EditText
    private lateinit var paso_12:EditText
    private lateinit var paso_13:EditText
    private lateinit var paso_14:EditText
    private lateinit var paso_15:EditText
    private lateinit var paso_16:EditText
    private lateinit var paso_17:EditText
    private lateinit var paso_18:EditText
    private lateinit var paso_19:EditText
    private lateinit var paso_20:EditText
    private lateinit var layout_paso_1:LinearLayout
    private lateinit var layout_paso_2:LinearLayout
    private lateinit var layout_paso_3:LinearLayout
    private lateinit var layout_paso_4:LinearLayout
    private lateinit var layout_paso_5:LinearLayout
    private lateinit var layout_paso_6:LinearLayout
    private lateinit var layout_paso_7:LinearLayout
    private lateinit var layout_paso_8:LinearLayout
    private lateinit var layout_paso_9:LinearLayout
    private lateinit var layout_paso_10:LinearLayout
    private lateinit var layout_paso_11:LinearLayout
    private lateinit var layout_paso_12:LinearLayout
    private lateinit var layout_paso_13:LinearLayout
    private lateinit var layout_paso_14:LinearLayout
    private lateinit var layout_paso_15:LinearLayout
    private lateinit var layout_paso_16:LinearLayout
    private lateinit var layout_paso_17:LinearLayout
    private lateinit var layout_paso_18:LinearLayout
    private lateinit var layout_paso_19:LinearLayout
    private lateinit var layout_paso_20:LinearLayout
    private lateinit var duracion:EditText
    private lateinit var anadirpaso:CardView
    private lateinit var boton_anadir:MaterialButton
    private lateinit var error_anadir_receta:TextView
    private lateinit var boton_editar:MaterialButton
    private val argumentos: FragmentoAnadirRecetaArgs by navArgs()

    private var imagen_seleccionada: Uri? = null
    private var num_ingredientes = 1
    private var num_pasos = 1


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


        estableceComportamientoAnadirPaso()

        anadirImagenPrincipal()

        procesaFormulario()

        return view
    }

    /*
        En este método obtenemos la imagen seleccionada por el usuario y la almacenamos en un atributo
        de la clase.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 100 && resultCode == Activity.RESULT_OK){
            imagen_seleccionada = data!!.data!!
            imagen_principal.setImageURI(imagen_seleccionada)

        }
    }

    /*
        Este método se encarga de comprobar que todos los campos sean correctos y devolverá un Boolean
        que indicará si hay o no errores en el formulario.
     */
    private fun compruebaCampos(modo:String):Boolean{
        var titulo = input_titulo_receta.text.toString()
        var duracion_aprox = duracion.text.toString()

        var error_campo_vacio = false

        //Comprobamos que el título esté relleno
        if(titulo == "") error_campo_vacio = true

        //Comprobamos que no se haya dejado ningún campo vacío de los ingredientes
        if(num_ingredientes == 1 && (ingrediente1.text.toString() == "" || cantidad_ingrediente1.text.toString() == "")) error_campo_vacio = true
        if(num_ingredientes == 2 && (ingrediente2.text.toString() == "" || cantidad_ingrediente2.text.toString() == "")) error_campo_vacio = true
        if(num_ingredientes == 3 && (ingrediente3.text.toString() == "" || cantidad_ingrediente3.text.toString() == "")) error_campo_vacio = true
        if(num_ingredientes == 4 && (ingrediente4.text.toString() == "" || cantidad_ingrediente4.text.toString() == "")) error_campo_vacio = true
        if(num_ingredientes == 5 && (ingrediente5.text.toString() == "" || cantidad_ingrediente5.text.toString() == "")) error_campo_vacio = true
        if(num_ingredientes == 6 && (ingrediente6.text.toString() == "" || cantidad_ingrediente6.text.toString() == "")) error_campo_vacio = true
        if(num_ingredientes == 7 && (ingrediente7.text.toString() == "" || cantidad_ingrediente7.text.toString() == "")) error_campo_vacio = true
        if(num_ingredientes == 8 && (ingrediente8.text.toString() == "" || cantidad_ingrediente8.text.toString() == "")) error_campo_vacio = true
        if(num_ingredientes == 9 && (ingrediente9.text.toString() == "" || cantidad_ingrediente9.text.toString() == "")) error_campo_vacio = true
        if(num_ingredientes == 10 && (ingrediente10.text.toString() == "" || cantidad_ingrediente10.text.toString() == "")) error_campo_vacio = true
        if(num_ingredientes == 11 && (ingrediente11.text.toString() == "" || cantidad_ingrediente11.text.toString() == "")) error_campo_vacio = true
        if(num_ingredientes == 12 && (ingrediente12.text.toString() == "" || cantidad_ingrediente12.text.toString() == "")) error_campo_vacio = true


        //Comprobamos que no se haya dejado ningún campo vacío de los pasos
        if(num_pasos == 1 && paso_1.text.toString() == "") error_campo_vacio = true
        if(num_pasos == 2 && paso_2.text.toString() == "") error_campo_vacio = true
        if(num_pasos == 3 && paso_3.text.toString() == "") error_campo_vacio = true
        if(num_pasos == 4 && paso_4.text.toString() == "") error_campo_vacio = true
        if(num_pasos == 5 && paso_5.text.toString() == "") error_campo_vacio = true
        if(num_pasos == 6 && paso_6.text.toString() == "") error_campo_vacio = true
        if(num_pasos == 7 && paso_7.text.toString() == "") error_campo_vacio = true
        if(num_pasos == 8 && paso_8.text.toString() == "") error_campo_vacio = true
        if(num_pasos == 9 && paso_9.text.toString() == "") error_campo_vacio = true
        if(num_pasos == 10 && paso_10.text.toString() == "") error_campo_vacio = true
        if(num_pasos == 11 && paso_11.text.toString() == "") error_campo_vacio = true
        if(num_pasos == 12 && paso_12.text.toString() == "") error_campo_vacio = true
        if(num_pasos == 13 && paso_13.text.toString() == "") error_campo_vacio = true
        if(num_pasos == 14 && paso_14.text.toString() == "") error_campo_vacio = true
        if(num_pasos == 15 && paso_15.text.toString() == "") error_campo_vacio = true
        if(num_pasos == 16 && paso_16.text.toString() == "") error_campo_vacio = true
        if(num_pasos == 17 && paso_17.text.toString() == "") error_campo_vacio = true
        if(num_pasos == 18 && paso_18.text.toString() == "") error_campo_vacio = true
        if(num_pasos == 19 && paso_19.text.toString() == "") error_campo_vacio = true
        if(num_pasos == 20 && paso_20.text.toString() == "") error_campo_vacio = true

        //Comprobamos que el campo duración no sea vacío
        if(duracion_aprox == "") error_campo_vacio = true

        //Comprobamos la imagen principal
        if(imagen_seleccionada == null && modo != "editar") error_campo_vacio = true

        //Comprobamos descripción
        if(input_descripcion.text.toString() == "") error_campo_vacio = true

        if(duracion_aprox.all { Character.isDigit(it) } == false) error_campo_vacio = true

        return error_campo_vacio
    }

    /*
        Este método se encarga de procesar el formulario comprobando que no hay errores y en caso
        de que no haya erres añadirá o editará la receta a la base de datos. En caso contrario mostrará
        un error para que el usuario corrija la situación.
     */
    private fun procesaFormulario(){
        if(argumentos.modo != null){ //Vamos a editar
            boton_anadir.visibility = View.GONE
            boton_editar.visibility = View.VISIBLE
            var id = argumentos.idreceta
            var db = obtenerBaseDatos(requireContext())
            var receta = db.recetaDao.obtenerPorID(id.toString())

            imagen_principal.setImageBitmap(receta.imagen)
            input_titulo_receta.setText(receta.nombreReceta.toString())
            input_descripcion.setText(receta.descripcion.toString())


            //Introducimos los ingredientes
            if(receta.num_ingredientes >= 1){
                    layout_ingrediente_1.visibility = View.VISIBLE
                    ingrediente1.setText(receta.ingrediente1.toString())
                    cantidad_ingrediente1.setText(receta.cantidad_ingrediente1.toString())
            }
            if(receta.num_ingredientes >= 2){
                    layout_ingrediente_2.visibility = View.VISIBLE
                    ingrediente2.setText(receta.ingrediente2.toString())
                    cantidad_ingrediente2.setText(receta.cantidad_ingrediente2.toString())
            }
            if(receta.num_ingredientes >=3){
                    layout_ingrediente_3.visibility = View.VISIBLE
                    ingrediente3.setText(receta.ingrediente3.toString())
                    cantidad_ingrediente3.setText(receta.cantidad_ingrediente3.toString())
            }
            if(receta.num_ingredientes >= 4){
                    layout_ingrediente_4.visibility = View.VISIBLE
                    ingrediente4.setText(receta.ingrediente4.toString())
                    cantidad_ingrediente4.setText(receta.cantidad_ingrediente4.toString())
            }
            if(receta.num_ingredientes >= 5){
                    layout_ingrediente_5.visibility = View.VISIBLE
                    ingrediente5.setText(receta.ingrediente5.toString())
                    cantidad_ingrediente5.setText(receta.cantidad_ingrediente5.toString())
            }
            if(receta.num_ingredientes >= 6){
                    layout_ingrediente_6.visibility = View.VISIBLE
                    ingrediente6.setText(receta.ingrediente6.toString())
                    cantidad_ingrediente6.setText(receta.cantidad_ingrediente6.toString())
            }
            if(receta.num_ingredientes >= 7){
                    layout_ingrediente_7.visibility = View.VISIBLE
                    ingrediente7.setText(receta.ingrediente7.toString())
                    cantidad_ingrediente7.setText(receta.cantidad_ingrediente7.toString())
            }
            if(receta.num_ingredientes >= 8){
                    layout_ingrediente_8.visibility = View.VISIBLE
                    ingrediente8.setText(receta.ingrediente8.toString())
                    cantidad_ingrediente8.setText(receta.cantidad_ingrediente8.toString())
            }
            if(receta.num_ingredientes >= 9){
                    layout_ingrediente_9.visibility = View.VISIBLE
                    ingrediente9.setText(receta.ingrediente9.toString())
                    cantidad_ingrediente9.setText(receta.cantidad_ingrediente9.toString())
            }
            if(receta.num_ingredientes >= 10){
                    layout_ingrediente_10.visibility = View.VISIBLE
                    ingrediente10.setText(receta.ingrediente10.toString())
                    cantidad_ingrediente10.setText(receta.cantidad_ingrediente10.toString())
            }
            if(receta.num_ingredientes >= 11){
                    layout_ingrediente_11.visibility = View.VISIBLE
                    ingrediente11.setText(receta.ingrediente11.toString())
                    cantidad_ingrediente11.setText(receta.cantidad_ingrediente11.toString())
            }
            if(receta.num_ingredientes >= 12){
                    layout_ingrediente_12.visibility = View.VISIBLE
                    ingrediente12.setText(receta.ingrediente12.toString())
                    cantidad_ingrediente12.setText(receta.cantidad_ingrediente12.toString())
            }

            //Introducimos los pasos
            if(receta.num_pasos >= 1){
                layout_paso_1.visibility = View.VISIBLE
                paso_1.setText(receta.paso1.toString())
            }
            if(receta.num_pasos >= 2){
                layout_paso_2.visibility = View.VISIBLE
                paso_2.setText(receta.paso2.toString())
            }
            if(receta.num_pasos >= 3){
                layout_paso_3.visibility = View.VISIBLE
                paso_3.setText(receta.paso3.toString())
            }
            if(receta.num_pasos >= 4){
                layout_paso_4.visibility = View.VISIBLE
                paso_4.setText(receta.paso4.toString())
            }
            if(receta.num_pasos >= 5){
                layout_paso_5.visibility = View.VISIBLE
                paso_5.setText(receta.paso5.toString())
            }
            if(receta.num_pasos >= 6){
                layout_paso_6.visibility = View.VISIBLE
                paso_6.setText(receta.paso6.toString())
            }
            if(receta.num_pasos >= 7){
                layout_paso_7.visibility = View.VISIBLE
                paso_7.setText(receta.paso7.toString())
            }
            if(receta.num_pasos >= 8){
                layout_paso_8.visibility = View.VISIBLE
                paso_8.setText(receta.paso8.toString())
            }
            if(receta.num_pasos >= 9){
                layout_paso_9.visibility = View.VISIBLE
                paso_9.setText(receta.paso9.toString())
            }
            if(receta.num_pasos >= 10){
                layout_paso_10.visibility = View.VISIBLE
                paso_10.setText(receta.paso10.toString())
            }
            if(receta.num_pasos >= 11){
                layout_paso_11.visibility = View.VISIBLE
                paso_11.setText(receta.paso11.toString())
            }
            if(receta.num_pasos >= 12){
                layout_paso_12.visibility = View.VISIBLE
                paso_12.setText(receta.paso12.toString())
            }
            if(receta.num_pasos >= 13){
                layout_paso_13.visibility = View.VISIBLE
                paso_13.setText(receta.paso13.toString())
            }
            if(receta.num_pasos >= 14){
                layout_paso_14.visibility = View.VISIBLE
                paso_14.setText(receta.paso14.toString())
            }
            if(receta.num_pasos >= 15){
                layout_paso_15.visibility = View.VISIBLE
                paso_15.setText(receta.paso15.toString())
            }
            if(receta.num_pasos >= 16){
                layout_paso_16.visibility = View.VISIBLE
                paso_16.setText(receta.paso16.toString())
            }
            if(receta.num_pasos >= 17){
                layout_paso_17.visibility = View.VISIBLE
                paso_17.setText(receta.paso17.toString())
            }
            if(receta.num_pasos >= 18){
                layout_paso_18.visibility = View.VISIBLE
                paso_18.setText(receta.paso18.toString())
            }
            if(receta.num_pasos >= 19){
                layout_paso_19.visibility = View.VISIBLE
                paso_19.setText(receta.paso19.toString())
            }
            if(receta.num_pasos >= 20){
                layout_paso_20.visibility = View.VISIBLE
                paso_20.setText(receta.paso20.toString())
            }

            //Duracion
            duracion.setText(receta.duracion.toString())

            //Categoria
            for(i in 0..(spiner_categoria.getAdapter().getCount()-1)){
                if(spiner_categoria.getAdapter().getItem(i).toString().contains(receta.categoria.toString())){
                    spiner_categoria.setSelection(i);
                }
            }



            num_ingredientes = receta.num_ingredientes
            num_pasos = receta.num_pasos



            boton_editar.setOnClickListener {
                //Imagen
                var imagen:Bitmap
                if(imagen_seleccionada != null){
                    imagen = MediaStore.Images.Media.getBitmap(context?.contentResolver, imagen_seleccionada)
                }
                else{
                    imagen = receta.imagen
                }

                var error_campo_vacio = compruebaCampos("editar")
                if(error_campo_vacio){
                    error_anadir_receta.visibility = View.VISIBLE
                    error_anadir_receta.text = "Debe rellenar todos los campos seleccionados"
                }
                else{ //la editamos
                    db.recetaDao.actualiza(Receta(receta.id, input_titulo_receta.text.toString(), input_descripcion.text.toString(), "",imagen, spiner_categoria.selectedItem.toString(),
                            ingrediente1.text.toString(), ingrediente2.text.toString(),
                            ingrediente3.text.toString(), ingrediente4.text.toString(),
                            ingrediente5.text.toString(), ingrediente6.text.toString(),
                            ingrediente7.text.toString(), ingrediente8.text.toString(),
                            ingrediente9.text.toString(), ingrediente10.text.toString(),
                            ingrediente11.text.toString(), ingrediente12.text.toString(),
                            cantidad_ingrediente1.text.toString(),
                            cantidad_ingrediente2.text.toString(),
                            cantidad_ingrediente3.text.toString(),
                            cantidad_ingrediente4.text.toString(),
                            cantidad_ingrediente5.text.toString(),
                            cantidad_ingrediente6.text.toString(),
                            cantidad_ingrediente7.text.toString(),
                            cantidad_ingrediente8.text.toString(),
                            cantidad_ingrediente9.text.toString(),
                            cantidad_ingrediente10.text.toString(),
                            cantidad_ingrediente11.text.toString(),
                            cantidad_ingrediente12.text.toString(),
                            duracion.text.toString().toInt(),
                            paso_1.text.toString(), paso_2.text.toString(),
                            paso_3.text.toString(), paso_4.text.toString(),
                            paso_5.text.toString(), paso_6.text.toString(),
                            paso_7.text.toString(), paso_8.text.toString(),
                            paso_9.text.toString(), paso_10.text.toString(),
                            paso_11.text.toString(), paso_12.text.toString(),
                            paso_13.text.toString(), paso_14.text.toString(),
                            paso_15.text.toString(), paso_16.text.toString(),
                            paso_17.text.toString(), paso_18.text.toString(),
                            paso_19.text.toString(), paso_20.text.toString(),
                            obtenerUsuarioLogueado()[0].username,
                            num_ingredientes,
                            num_pasos,
                            "Fácil"
                    ))
                    (activity as MainActivity).fromAnadirRecetaToPerfil()
                }
            }
        }
        else{ //vamos a añadir
            boton_anadir.setOnClickListener {
                var error_campo_vacio = compruebaCampos("")

                if(error_campo_vacio){
                    error_anadir_receta.visibility = View.VISIBLE
                    error_anadir_receta.text = "Debe rellenar todos los campos seleccionados"
                }
                else{
                    var imagen = MediaStore.Images.Media.getBitmap(context?.contentResolver, imagen_seleccionada)

                    var db = obtenerBaseDatos(requireContext())

                    db.recetaDao.insertaUna(Receta(0, input_titulo_receta.text.toString(), input_descripcion.text.toString(), "",imagen, spiner_categoria.selectedItem.toString(),
                            ingrediente1.text.toString(), ingrediente2.text.toString(),
                            ingrediente3.text.toString(), ingrediente4.text.toString(),
                            ingrediente5.text.toString(), ingrediente6.text.toString(),
                            ingrediente7.text.toString(), ingrediente8.text.toString(),
                            ingrediente9.text.toString(), ingrediente10.text.toString(),
                            ingrediente11.text.toString(), ingrediente12.text.toString(),
                            cantidad_ingrediente1.text.toString(),
                            cantidad_ingrediente2.text.toString(),
                            cantidad_ingrediente3.text.toString(),
                            cantidad_ingrediente4.text.toString(),
                            cantidad_ingrediente5.text.toString(),
                            cantidad_ingrediente6.text.toString(),
                            cantidad_ingrediente7.text.toString(),
                            cantidad_ingrediente8.text.toString(),
                            cantidad_ingrediente9.text.toString(),
                            cantidad_ingrediente10.text.toString(),
                            cantidad_ingrediente11.text.toString(),
                            cantidad_ingrediente12.text.toString(),
                            duracion.text.toString().toInt(),
                            paso_1.text.toString(), paso_2.text.toString(),
                            paso_3.text.toString(), paso_4.text.toString(),
                            paso_5.text.toString(), paso_6.text.toString(),
                            paso_7.text.toString(), paso_8.text.toString(),
                            paso_9.text.toString(), paso_10.text.toString(),
                            paso_11.text.toString(), paso_12.text.toString(),
                            paso_13.text.toString(), paso_14.text.toString(),
                            paso_15.text.toString(), paso_16.text.toString(),
                            paso_17.text.toString(), paso_18.text.toString(),
                            paso_19.text.toString(), paso_20.text.toString(),
                            obtenerUsuarioLogueado()[0].username,
                            num_ingredientes,
                            num_pasos,
                            "Fácil"
                    ))
                    (activity as MainActivity).fromAnadirRecetaToPerfil()
                }
            }
        }

    }

    /*
        Este método se encarga de comprobar si hay un usuario que haya iniciado sesión. En caso afirmativo
        devuelve el usuario, en caso contrario devuelve null.
     */
    private fun obtenerUsuarioLogueado():MutableList<Usuario>{
        var sharedPreferences: SharedPreferences = requireContext().applicationContext.getSharedPreferences("ajustes",0)
        var user = sharedPreferences.getString("usuario", String())

        var db = obtenerBaseDatos(requireContext())
        var user_db = db.usuarioDao.obtenerPorNombre(user.toString())

        return user_db
    }
    /*
        Este método se encarga de definir el intent que abrirá la galería así como el código utilizado
        para capturar la respuesta.
     */
    private fun anadirImagenPrincipal(){
        boton_seleccion_imagen.setOnClickListener {
            var lanzador_seleccion = Intent(Intent.ACTION_PICK)
            lanzador_seleccion.type = "image/*"
            startActivityForResult(lanzador_seleccion, 100)
        }
    }

    /*
        Este método se encarga de añadir al layout una nueva casilla para introducir un nuevo paso
        cuando se pulsa al botón de añadir un nuevo paso.
     */
    public fun estableceComportamientoAnadirPaso(){
        anadirpaso.setOnClickListener {
            var paso = primerPasoVacio()

            if(paso != 0){
                when(paso){
                    2->{
                        if(layout_paso_2.visibility == View.GONE){
                            num_pasos++
                        }
                        layout_paso_2.visibility = View.VISIBLE
                    }
                    3->{
                        if(layout_paso_3.visibility == View.GONE){
                            num_pasos++
                        }
                        layout_paso_3.visibility = View.VISIBLE
                    }
                    4->{
                        if(layout_paso_4.visibility == View.GONE){
                            num_pasos++
                        }
                        layout_paso_4.visibility = View.VISIBLE
                    }
                    5->{
                        if(layout_paso_5.visibility == View.GONE){
                            num_pasos++
                        }
                        layout_paso_5.visibility = View.VISIBLE
                    }
                    6->{
                        if(layout_paso_6.visibility == View.GONE){
                            num_pasos++
                        }
                        layout_paso_6.visibility = View.VISIBLE
                    }
                    7->{
                        if(layout_paso_7.visibility == View.GONE){
                            num_pasos++
                        }
                        layout_paso_7.visibility = View.VISIBLE
                    }
                    8->{
                        if(layout_paso_8.visibility == View.GONE){
                            num_pasos++
                        }
                        layout_paso_8.visibility = View.VISIBLE
                    }
                    9->{
                        if(layout_paso_9.visibility == View.GONE){
                            num_pasos++
                        }
                        layout_paso_9.visibility = View.VISIBLE
                    }
                    10->{
                        if(layout_paso_10.visibility == View.GONE){
                            num_pasos++
                        }
                        layout_paso_10.visibility = View.VISIBLE
                    }
                    11->{
                        if(layout_paso_11.visibility == View.GONE){
                            num_pasos++
                        }
                        layout_paso_11.visibility = View.VISIBLE
                    }
                    12->{
                        if(layout_paso_12.visibility == View.GONE){
                            num_pasos++
                        }
                        layout_paso_12.visibility = View.VISIBLE
                    }
                    13->{
                        if(layout_paso_13.visibility == View.GONE){
                            num_pasos++
                        }
                        layout_paso_13.visibility = View.VISIBLE
                    }
                    14->{
                        if(layout_paso_14.visibility == View.GONE){
                            num_pasos++
                        }
                        layout_paso_14.visibility = View.VISIBLE
                    }
                    15->{
                        if(layout_paso_15.visibility == View.GONE){
                            num_pasos++
                        }
                        layout_paso_15.visibility = View.VISIBLE
                    }
                    16->{
                        if(layout_paso_16.visibility == View.GONE){
                            num_pasos++
                        }
                        layout_paso_16.visibility = View.VISIBLE
                    }
                    17->{
                        if(layout_paso_17.visibility == View.GONE){
                            num_pasos++
                        }
                        layout_paso_17.visibility = View.VISIBLE
                    }
                    18->{
                        if(layout_paso_18.visibility == View.GONE){
                            num_pasos++
                        }
                        layout_paso_18.visibility = View.VISIBLE
                    }
                    19->{
                        if(layout_paso_19.visibility == View.GONE){
                            num_pasos++
                        }
                        layout_paso_19.visibility = View.VISIBLE
                    }
                    20->{
                        if(layout_paso_20.visibility == View.GONE){
                            num_pasos++
                        }
                        layout_paso_20.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    /*
        Este método se encarga de averiguar cual es el siguiente paso a habilitar para que un
        usuario lo añada.
     */
    public fun primerPasoVacio():Int{
        var vacios:ArrayList<Int> = arrayListOf()
        if(paso_2.text.toString() == "") vacios.add(2)
        if(paso_3.text.toString() == "") vacios.add(3)
        if(paso_4.text.toString() == "") vacios.add(4)
        if(paso_5.text.toString() == "") vacios.add(5)
        if(paso_6.text.toString() == "") vacios.add(6)
        if(paso_7.text.toString() == "") vacios.add(7)
        if(paso_8.text.toString() == "") vacios.add(8)
        if(paso_9.text.toString() == "") vacios.add(9)
        if(paso_10.text.toString() == "") vacios.add(10)
        if(paso_11.text.toString() == "") vacios.add(11)
        if(paso_12.text.toString() == "") vacios.add(12)
        if(paso_13.text.toString() == "") vacios.add(13)
        if(paso_14.text.toString() == "") vacios.add(14)
        if(paso_15.text.toString() == "") vacios.add(15)
        if(paso_16.text.toString() == "") vacios.add(16)
        if(paso_17.text.toString() == "") vacios.add(17)
        if(paso_18.text.toString() == "") vacios.add(18)
        if(paso_19.text.toString() == "") vacios.add(19)
        if(paso_20.text.toString() == "") vacios.add(20)


        if(vacios.size == 0 ||  vacios.size == 1){
            anadirpaso.visibility = View.GONE
        }
        else{
            anadirpaso.visibility = View.VISIBLE
        }

        if(vacios.size > 0){
            return vacios[0]
        }
        else{
            return 0
        }
    }

    /*
        Este método se encarga de inicializar el spinner de categorías.
     */
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
                        if(layout_ingrediente_2.visibility == View.GONE){
                            num_ingredientes++
                        }
                        layout_ingrediente_2.visibility = View.VISIBLE
                    }
                    3->{
                        if(layout_ingrediente_3.visibility == View.GONE){
                            num_ingredientes++
                        }
                        layout_ingrediente_3.visibility = View.VISIBLE
                    }
                    4->{
                        if(layout_ingrediente_4.visibility == View.GONE){
                            num_ingredientes++
                        }
                        layout_ingrediente_4.visibility = View.VISIBLE
                    }
                    5->{
                        if(layout_ingrediente_5.visibility == View.GONE){
                            num_ingredientes++
                        }
                        layout_ingrediente_5.visibility = View.VISIBLE
                    }
                    6->{
                        if(layout_ingrediente_6.visibility == View.GONE){
                            num_ingredientes++
                        }
                        layout_ingrediente_6.visibility = View.VISIBLE
                    }
                    7->{
                        if(layout_ingrediente_7.visibility == View.GONE){
                            num_ingredientes++
                        }
                        layout_ingrediente_7.visibility = View.VISIBLE
                    }
                    8->{
                        if(layout_ingrediente_8.visibility == View.GONE){
                            num_ingredientes++
                        }
                        layout_ingrediente_8.visibility = View.VISIBLE
                    }
                    9->{
                        if(layout_ingrediente_9.visibility == View.GONE){
                            num_ingredientes++
                        }
                        layout_ingrediente_9.visibility = View.VISIBLE
                    }
                    10->{
                        if(layout_ingrediente_10.visibility == View.GONE){
                            num_ingredientes++
                        }
                        layout_ingrediente_10.visibility = View.VISIBLE
                    }
                    11->{
                        if(layout_ingrediente_11.visibility == View.GONE){
                            num_ingredientes++
                        }
                        layout_ingrediente_11.visibility = View.VISIBLE
                    }
                    12->{
                        if(layout_ingrediente_12.visibility == View.GONE){
                            num_ingredientes++
                        }
                        layout_ingrediente_12.visibility = View.VISIBLE
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
        if(ingrediente7.text.toString() == "") vacios.add(7)
        if(ingrediente8.text.toString() == "") vacios.add(8)
        if(ingrediente9.text.toString() == "") vacios.add(9)
        if(ingrediente10.text.toString() == "") vacios.add(10)
        if(ingrediente11.text.toString() == "") vacios.add(11)
        if(ingrediente12.text.toString() == "") vacios.add(12)


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
        ingrediente7 = view.findViewById(R.id.ingrediente7)
        cantidad_ingrediente7 = view.findViewById(R.id.cantidad_ingrediente7)
        layout_ingrediente_7 = view.findViewById(R.id.layout_ingrediente_7)
        ingrediente8 = view.findViewById(R.id.ingrediente8)
        cantidad_ingrediente8 = view.findViewById(R.id.cantidad_ingrediente8)
        layout_ingrediente_8 = view.findViewById(R.id.layout_ingrediente_8)
        ingrediente9 = view.findViewById(R.id.ingrediente9)
        cantidad_ingrediente9 = view.findViewById(R.id.cantidad_ingrediente9)
        layout_ingrediente_9 = view.findViewById(R.id.layout_ingrediente_9)
        ingrediente10 = view.findViewById(R.id.ingrediente10)
        cantidad_ingrediente10 = view.findViewById(R.id.cantidad_ingrediente10)
        layout_ingrediente_10 = view.findViewById(R.id.layout_ingrediente_10)
        ingrediente11 = view.findViewById(R.id.ingrediente11)
        cantidad_ingrediente11 = view.findViewById(R.id.cantidad_ingrediente11)
        layout_ingrediente_11 = view.findViewById(R.id.layout_ingrediente_11)
        ingrediente12 = view.findViewById(R.id.ingrediente12)
        cantidad_ingrediente12 = view.findViewById(R.id.cantidad_ingrediente12)
        layout_ingrediente_12 = view.findViewById(R.id.layout_ingrediente_12)

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
        paso_11 = view.findViewById(R.id.paso_11)
        paso_12 = view.findViewById(R.id.paso_12)
        paso_13 = view.findViewById(R.id.paso_13)
        paso_14 = view.findViewById(R.id.paso_14)
        paso_15 = view.findViewById(R.id.paso_15)
        paso_16 = view.findViewById(R.id.paso_16)
        paso_17 = view.findViewById(R.id.paso_17)
        paso_18 = view.findViewById(R.id.paso_18)
        paso_19 = view.findViewById(R.id.paso_19)
        paso_20 = view.findViewById(R.id.paso_20)
        duracion = view.findViewById(R.id.duracion)
        anadirpaso = view.findViewById(R.id.anadirpaso)
        boton_anadir = view.findViewById(R.id.boton_anadir)
        layout_paso_1 = view.findViewById(R.id.layout_paso_1)
        layout_paso_2 = view.findViewById(R.id.layout_paso_2)
        layout_paso_3 = view.findViewById(R.id.layout_paso_3)
        layout_paso_4 = view.findViewById(R.id.layout_paso_4)
        layout_paso_5 = view.findViewById(R.id.layout_paso_5)
        layout_paso_6 = view.findViewById(R.id.layout_paso_6)
        layout_paso_7 = view.findViewById(R.id.layout_paso_7)
        layout_paso_8 = view.findViewById(R.id.layout_paso_8)
        layout_paso_9 = view.findViewById(R.id.layout_paso_9)
        layout_paso_10 = view.findViewById(R.id.layout_paso_10)
        layout_paso_11 = view.findViewById(R.id.layout_paso_11)
        layout_paso_12 = view.findViewById(R.id.layout_paso_12)
        layout_paso_13 = view.findViewById(R.id.layout_paso_13)
        layout_paso_14 = view.findViewById(R.id.layout_paso_14)
        layout_paso_15 = view.findViewById(R.id.layout_paso_15)
        layout_paso_16 = view.findViewById(R.id.layout_paso_16)
        layout_paso_17 = view.findViewById(R.id.layout_paso_17)
        layout_paso_18 = view.findViewById(R.id.layout_paso_18)
        layout_paso_19 = view.findViewById(R.id.layout_paso_19)
        layout_paso_20 = view.findViewById(R.id.layout_paso_20)
        error_anadir_receta = view.findViewById(R.id.error_anadir_receta)
        input_descripcion = view.findViewById(R.id.input_descripcion)
        boton_editar = view.findViewById(R.id.boton_editar)
    }

}