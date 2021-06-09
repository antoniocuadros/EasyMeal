package com.acl.easymeal.fragmentos

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.navigation.fragment.navArgs
import com.acl.easymeal.MainActivity
import com.acl.easymeal.R
import com.acl.easymeal.modelo.Receta
import com.acl.easymeal.modelo.Usuario
import com.acl.easymeal.modelo.obtenerBaseDatos
import com.google.android.material.button.MaterialButton
import com.vansuita.pickimage.bundle.PickSetup
import java.io.ByteArrayOutputStream
import java.net.URI
/*
    La clase FragmentoAnadirReceta se encarga de dotar de funcionalidad al apartado de la aplicación
    encargado de tanto añadir una receta como de editarlo.
 */
/*
    Los atributos de esta clase son:
        -> boton_seleccion_imagen: Botón que permite seleccionar la imagen principal, de tipo CardView.
        -> imagen_principal: Imagen principal de la receta, de tipo ImageView.
        -> input_titulo_receta: Casilla rellenable que permite escribir el título de la receta, de tipo EditText.
        -> input_descripcion: Casilla rellenable que permite escribir la descripción de la receta, de tipo EditText.
        -> spiner_categoria: Selector que nos permite elegir una categoría, de tipo Spinner.
        -> layout_ingrediente_1...layout_ingrediente_12: Layouts de los ingredientes que nos permitirán ir ocultando y visualizando los mismos, de tipo LinearLayout.
        -> ingrediente1...ingrediente12: Casilla rellenable que nos permite escribir el nombre del ingrediente, de tipo EditText.
        -> cantidad_ingrediente1...cantidad_ingrediente12: Casilla rellenable que nos permite escribir la cantidad del ingrediente, de tipo EditText.
        -> paso_1...paso_20: Casilla rellenable que nos permite escribir la descripción de un determinado paso, de tipo EditText.
        -> layout_paso_1...layout_paso_20: Layouts de los pasos que nos permitirán ir ocultando y visualizando los mismos, de tipo LinearLayout.
        -> duracion: Casilla rellenable que nos permite indicar la duración en minutos de la receta, de tipo EditText.
        -> anadirpaso: Botón que nos permite añadir un nuevo paso, de tipo CardView.
        -> anadiringrediente: Botón que nos permite añadir un nuevo ingrediente, de tipo CardView.
        -> boton_anadir: Botón que nos permite añadir una nueva receta, de tipo MaterialButton.
        -> error_anadir_receta: Texto que nos indica si hay algún error, de tipo TextView.
        -> boton_editar: Botón que nos permite editar una receta, de tipo MaterialButton.
        -> argumentos: Atributo de tipo FragmentoAnadirRecetaArgs que nos permitirá obtener la receta que se desee editar.
        -> check_thermomix: Casilla marcable que permite indicar si la receta se hace o no con Thermomix, de tipo CheckBox.
        -> spiner_dificultad: spinner que nos permite elegir la dificultad de la receta, de tipo Spinner.
        -> imagen_paso_1...imagen_paso_20: ImageView que permitirá elegir y mostrar una imagen para un determinado paso.
        -> imagen1...imagen20: Imágenes asociadas a cada uno de los pasos, de tipo BitMap.
        -> num_ingredientes: número de ingredientes de la receta, de tipo Int.
        -> num_pasos: número de pasos de la receta, de tipo Int.
 */
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
    private lateinit var check_thermomix:CheckBox
    private lateinit var spiner_dificultad:Spinner
    private lateinit var imagen_paso_1:ImageView
    private var imagen1:Bitmap? = null
    private lateinit var imagen_paso_2:ImageView
    private var imagen2:Bitmap? = null
    private lateinit var imagen_paso_3:ImageView
    private var imagen3:Bitmap? = null
    private lateinit var imagen_paso_4:ImageView
    private var imagen4:Bitmap? = null
    private lateinit var imagen_paso_5:ImageView
    private var imagen5:Bitmap? = null
    private lateinit var imagen_paso_6:ImageView
    private var imagen6:Bitmap? = null
    private lateinit var imagen_paso_7:ImageView
    private var imagen7:Bitmap? = null
    private lateinit var imagen_paso_8:ImageView
    private var imagen8:Bitmap? = null
    private lateinit var imagen_paso_9:ImageView
    private var imagen9:Bitmap? = null
    private lateinit var imagen_paso_10:ImageView
    private var imagen10:Bitmap? = null
    private lateinit var imagen_paso_11:ImageView
    private var imagen11:Bitmap? = null
    private lateinit var imagen_paso_12:ImageView
    private var imagen12:Bitmap? = null
    private lateinit var imagen_paso_13:ImageView
    private var imagen13:Bitmap? = null
    private lateinit var imagen_paso_14:ImageView
    private var imagen14:Bitmap? = null
    private lateinit var imagen_paso_15:ImageView
    private var imagen15:Bitmap? = null
    private lateinit var imagen_paso_16:ImageView
    private var imagen16:Bitmap? = null
    private lateinit var imagen_paso_17:ImageView
    private var imagen17:Bitmap? = null
    private lateinit var imagen_paso_18:ImageView
    private var imagen18:Bitmap? = null
    private lateinit var imagen_paso_19:ImageView
    private var imagen19:Bitmap? = null
    private lateinit var imagen_paso_20:ImageView
    private var imagen20:Bitmap? = null
    private var imagen_seleccionada: Bitmap? = null
    private var num_ingredientes = 1
    private var num_pasos = 1

/*
    En el método onCreate de un fragmento es llamado durante la creación del mismo.
 */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
/*
    En este método se realizan los siguientes pasos:
        -> 1) Se obtiene la vista.
        -> 2) Se llama al método vinculaVistasVariables que vincula cada atributo de tipo vista con su elemento del layout.
        -> 3) Se llama al método inicializaCategorías, que inicializa el spinner de categorías.
        -> 4) Se llama al método estableceComportamientoBotonAnadirIngrediente que define el comportamiento de los botones relacionados con los ingredientes.
        -> 5) Se llama al método estableceComportamientoAnadirPaso que define el comportamiento de lo relacionado con los pasos.
        -> 6) Se llama al método anadirImagenPrincipal que define el comportamiento para añadir imágenes principales.
        -> 7) Se llama al método procesaFormulario que procesará los formularios de las recetas cuando se pulsen los botones de añadir o editar.
        -> 8) Se devuelve la vista.
 */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Paso 1
        val view =  inflater.inflate(R.layout.fragmento_anadir_receta, container, false)

        //Paso 2
        vinculaVistasVariables(view)

        //Paso 3
        inicializaCategorías()

        //Paso 4
        estableceComportamientoBotonAnadirIngrediente()

        //Paso 5
        estableceComportamientoAnadirPaso()

        //Paso 6
        anadirImagenPrincipal()

        //Paso 7
        procesaFormulario()

        //Paso 8
        return view
    }

    /*
        Este método define el diálogo para que un usuario pueda elegir una imagen desde la cámara
        o desde la galería así como implementa el comportamiento para elegir una imagen de la galería
        o de la cámara.
     */
    private fun seleccionaImagen(code:Int){
        var builder:AlertDialog.Builder = AlertDialog.Builder(requireContext());
        builder.setTitle("Elija desde donde obtener la imagen")


        builder.setPositiveButton("Galería"){dialogo, _ ->
            dialogo.dismiss()

            var lanzador_seleccion = Intent(Intent.ACTION_PICK)
            lanzador_seleccion.type = "image/*"
            startActivityForResult(lanzador_seleccion, code)
        }
        builder.setNegativeButton("Cámara"){dialogo, _ ->
            dialogo.dismiss()
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED
                        || checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                    var permisos = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    requestPermissions(permisos, 101)
                }
                else{
                    val lanzador_camara = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(lanzador_camara, code+1)
                }
            }
            else{
                val lanzador_camara = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(lanzador_camara, code+1)
            }
        }

        var dialogo = builder.create()
        dialogo.show()
    }

    /*
        Este método se encarga de definir el comportamiento de los botones para añadir imágenes
        en cada paso de la receta.
     */
    private fun inicializaListenersImágenesPasos(){
        imagen_paso_1.setOnClickListener {
            seleccionaImagen(200)
        }
        imagen_paso_2.setOnClickListener {
            seleccionaImagen(202)
        }
        imagen_paso_3.setOnClickListener {
            seleccionaImagen(204)
        }
        imagen_paso_4.setOnClickListener {
            seleccionaImagen(206)
        }
        imagen_paso_5.setOnClickListener {
            seleccionaImagen(208)
        }
        imagen_paso_6.setOnClickListener {
            seleccionaImagen(210)
        }
        imagen_paso_7.setOnClickListener {
            seleccionaImagen(212)
        }
        imagen_paso_8.setOnClickListener {
            seleccionaImagen(214)
        }
        imagen_paso_9.setOnClickListener {
            seleccionaImagen(216)
        }
        imagen_paso_10.setOnClickListener {
            seleccionaImagen(218)
        }
        imagen_paso_11.setOnClickListener {
            seleccionaImagen(220)
        }
        imagen_paso_12.setOnClickListener {
            seleccionaImagen(222)
        }
        imagen_paso_13.setOnClickListener {
            seleccionaImagen(224)
        }
        imagen_paso_14.setOnClickListener {
            seleccionaImagen(226)
        }
        imagen_paso_15.setOnClickListener {
            seleccionaImagen(228)
        }
        imagen_paso_16.setOnClickListener {
            seleccionaImagen(230)
        }
        imagen_paso_17.setOnClickListener {
            seleccionaImagen(232)
        }
        imagen_paso_18.setOnClickListener {
            seleccionaImagen(234)
        }
        imagen_paso_19.setOnClickListener {
            seleccionaImagen(236)
        }
        imagen_paso_20.setOnClickListener {
            seleccionaImagen(238)
        }
    }

    /*
        En este método obtenemos la imagen seleccionada por el usuario y la almacenamos en un atributo
        de la clase.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //////////////////////
        //
        // GALERÍA
        //
        //////////////////////
        if(requestCode == 100 && resultCode == Activity.RESULT_OK){
            var imagen_seleccionada2 = data!!.data!!
            imagen_seleccionada =  MediaStore.Images.Media.getBitmap(context?.contentResolver, imagen_seleccionada2)
            imagen_principal.setImageBitmap(imagen_seleccionada)

        }
        if(requestCode == 200 && resultCode == Activity.RESULT_OK){
            var imagen_seleccionada2 = data!!.data!!
            imagen1 =  MediaStore.Images.Media.getBitmap(context?.contentResolver, imagen_seleccionada2)
            imagen_paso_1.setImageBitmap(imagen1)

        }
        if(requestCode == 202 && resultCode == Activity.RESULT_OK){
            var imagen_seleccionada2 = data!!.data!!
            imagen2 =  MediaStore.Images.Media.getBitmap(context?.contentResolver, imagen_seleccionada2)
            imagen_paso_2.setImageBitmap(imagen2)
        }
        if(requestCode == 204 && resultCode == Activity.RESULT_OK){
            var imagen_seleccionada2 = data!!.data!!
            imagen3 =  MediaStore.Images.Media.getBitmap(context?.contentResolver, imagen_seleccionada2)
            imagen_paso_3.setImageBitmap(imagen3)
        }
        if(requestCode == 206 && resultCode == Activity.RESULT_OK){
            var imagen_seleccionada2 = data!!.data!!
            imagen4 =  MediaStore.Images.Media.getBitmap(context?.contentResolver, imagen_seleccionada2)
            imagen_paso_4.setImageBitmap(imagen4)
        }
        if(requestCode == 208 && resultCode == Activity.RESULT_OK){
            var imagen_seleccionada2 = data!!.data!!
            imagen5 =  MediaStore.Images.Media.getBitmap(context?.contentResolver, imagen_seleccionada2)
            imagen_paso_5.setImageBitmap(imagen5)
        }
        if(requestCode == 210 && resultCode == Activity.RESULT_OK){
            var imagen_seleccionada2 = data!!.data!!
            imagen6 =  MediaStore.Images.Media.getBitmap(context?.contentResolver, imagen_seleccionada2)
            imagen_paso_6.setImageBitmap(imagen6)
        }
        if(requestCode == 212 && resultCode == Activity.RESULT_OK){
            var imagen_seleccionada2 = data!!.data!!
            imagen7 =  MediaStore.Images.Media.getBitmap(context?.contentResolver, imagen_seleccionada2)
            imagen_paso_7.setImageBitmap(imagen7)
        }
        if(requestCode == 214 && resultCode == Activity.RESULT_OK){
            var imagen_seleccionada2 = data!!.data!!
            imagen8 =  MediaStore.Images.Media.getBitmap(context?.contentResolver, imagen_seleccionada2)
            imagen_paso_8.setImageBitmap(imagen8)
        }
        if(requestCode == 216 && resultCode == Activity.RESULT_OK){
            var imagen_seleccionada2 = data!!.data!!
            imagen9 =  MediaStore.Images.Media.getBitmap(context?.contentResolver, imagen_seleccionada2)
            imagen_paso_9.setImageBitmap(imagen9)
        }
        if(requestCode == 218 && resultCode == Activity.RESULT_OK){
            var imagen_seleccionada2 = data!!.data!!
            imagen10 =  MediaStore.Images.Media.getBitmap(context?.contentResolver, imagen_seleccionada2)
            imagen_paso_10.setImageBitmap(imagen10)
        }

        if(requestCode == 220 && resultCode == Activity.RESULT_OK){
            var imagen_seleccionada2 = data!!.data!!
            imagen11 =  MediaStore.Images.Media.getBitmap(context?.contentResolver, imagen_seleccionada2)
            imagen_paso_11.setImageBitmap(imagen11)
        }
        if(requestCode == 222 && resultCode == Activity.RESULT_OK){
            var imagen_seleccionada2 = data!!.data!!
            imagen12 =  MediaStore.Images.Media.getBitmap(context?.contentResolver, imagen_seleccionada2)
            imagen_paso_12.setImageBitmap(imagen12)
        }
        if(requestCode == 224 && resultCode == Activity.RESULT_OK){
            var imagen_seleccionada2 = data!!.data!!
            imagen13 =  MediaStore.Images.Media.getBitmap(context?.contentResolver, imagen_seleccionada2)
            imagen_paso_13.setImageBitmap(imagen13)
        }
        if(requestCode == 226 && resultCode == Activity.RESULT_OK){
            var imagen_seleccionada2 = data!!.data!!
            imagen14 =  MediaStore.Images.Media.getBitmap(context?.contentResolver, imagen_seleccionada2)
            imagen_paso_14.setImageBitmap(imagen14)


        }
        if(requestCode == 228 && resultCode == Activity.RESULT_OK){
            var imagen_seleccionada2 = data!!.data!!
            imagen15 =  MediaStore.Images.Media.getBitmap(context?.contentResolver, imagen_seleccionada2)
            imagen_paso_15.setImageBitmap(imagen15)
        }
        if(requestCode == 230 && resultCode == Activity.RESULT_OK){
            var imagen_seleccionada2 = data!!.data!!
            imagen16 =  MediaStore.Images.Media.getBitmap(context?.contentResolver, imagen_seleccionada2)
            imagen_paso_16.setImageBitmap(imagen16)
        }
        if(requestCode == 232 && resultCode == Activity.RESULT_OK){
            var imagen_seleccionada2 = data!!.data!!
            imagen17 =  MediaStore.Images.Media.getBitmap(context?.contentResolver, imagen_seleccionada2)
            imagen_paso_17.setImageBitmap(imagen17)
        }
        if(requestCode == 234 && resultCode == Activity.RESULT_OK){
            var imagen_seleccionada2 = data!!.data!!
            imagen18 =  MediaStore.Images.Media.getBitmap(context?.contentResolver, imagen_seleccionada2)
            imagen_paso_18.setImageBitmap(imagen18)
        }
        if(requestCode == 236 && resultCode == Activity.RESULT_OK){
            var imagen_seleccionada2 = data!!.data!!
            imagen19 =  MediaStore.Images.Media.getBitmap(context?.contentResolver, imagen_seleccionada2)
            imagen_paso_19.setImageBitmap(imagen19)
        }
        if(requestCode == 238 && resultCode == Activity.RESULT_OK){
            var imagen_seleccionada2 = data!!.data!!
            imagen20 =  MediaStore.Images.Media.getBitmap(context?.contentResolver, imagen_seleccionada2)
            imagen_paso_20.setImageBitmap(imagen20)
        }

        //////////////////////
        //
        // CÁMARA
        //
        //////////////////////

        if(requestCode == 111 && resultCode == Activity.RESULT_OK){
            imagen_seleccionada = data!!.extras?.get("data") as Bitmap
            imagen_principal.setImageBitmap(imagen_seleccionada)
        }
        if(requestCode == 201 && resultCode == Activity.RESULT_OK){
            imagen1 = data!!.extras?.get("data") as Bitmap
            imagen_paso_1.setImageBitmap(imagen1)
        }
        if(requestCode == 203 && resultCode == Activity.RESULT_OK){
            imagen2 = data!!.extras?.get("data") as Bitmap
            imagen_paso_2.setImageBitmap(imagen2)
        }
        if(requestCode == 205 && resultCode == Activity.RESULT_OK){
            imagen3 = data!!.extras?.get("data") as Bitmap
            imagen_paso_3.setImageBitmap(imagen3)
        }
        if(requestCode == 207 && resultCode == Activity.RESULT_OK){
            imagen4 = data!!.extras?.get("data") as Bitmap
            imagen_paso_4.setImageBitmap(imagen4)
        }
        if(requestCode == 209 && resultCode == Activity.RESULT_OK){
            imagen5 = data!!.extras?.get("data") as Bitmap
            imagen_paso_5.setImageBitmap(imagen5)
        }
        if(requestCode == 211 && resultCode == Activity.RESULT_OK){
            imagen6 = data!!.extras?.get("data") as Bitmap
            imagen_paso_6.setImageBitmap(imagen6)
        }
        if(requestCode == 213 && resultCode == Activity.RESULT_OK){
            imagen7 = data!!.extras?.get("data") as Bitmap
            imagen_paso_7.setImageBitmap(imagen7)
        }
        if(requestCode == 215 && resultCode == Activity.RESULT_OK){
            imagen8 = data!!.extras?.get("data") as Bitmap
            imagen_paso_8.setImageBitmap(imagen8)
        }
        if(requestCode == 217 && resultCode == Activity.RESULT_OK){
            imagen9 = data!!.extras?.get("data") as Bitmap
            imagen_paso_9.setImageBitmap(imagen9)
        }
        if(requestCode == 219 && resultCode == Activity.RESULT_OK){
            imagen10 = data!!.extras?.get("data") as Bitmap
            imagen_paso_10.setImageBitmap(imagen10)
        }
        if(requestCode == 221 && resultCode == Activity.RESULT_OK){
            imagen11 = data!!.extras?.get("data") as Bitmap
            imagen_paso_11.setImageBitmap(imagen11)
        }
        if(requestCode == 223 && resultCode == Activity.RESULT_OK){
            imagen12 = data!!.extras?.get("data") as Bitmap
            imagen_paso_12.setImageBitmap(imagen12)
        }
        if(requestCode == 225 && resultCode == Activity.RESULT_OK){
            imagen13 = data!!.extras?.get("data") as Bitmap
            imagen_paso_13.setImageBitmap(imagen13)
        }
        if(requestCode == 227 && resultCode == Activity.RESULT_OK){
            imagen14 = data!!.extras?.get("data") as Bitmap
            imagen_paso_14.setImageBitmap(imagen14)
        }
        if(requestCode == 229 && resultCode == Activity.RESULT_OK){
            imagen15 = data!!.extras?.get("data") as Bitmap
            imagen_paso_15.setImageBitmap(imagen15)
        }
        if(requestCode == 231 && resultCode == Activity.RESULT_OK){
            imagen16 = data!!.extras?.get("data") as Bitmap
            imagen_paso_16.setImageBitmap(imagen16)
        }
        if(requestCode == 233 && resultCode == Activity.RESULT_OK){
            imagen17 = data!!.extras?.get("data") as Bitmap
            imagen_paso_17.setImageBitmap(imagen17)
        }
        if(requestCode == 235 && resultCode == Activity.RESULT_OK){
            imagen18 = data!!.extras?.get("data") as Bitmap
            imagen_paso_18.setImageBitmap(imagen18)
        }
        if(requestCode == 237 && resultCode == Activity.RESULT_OK){
            imagen19 = data!!.extras?.get("data") as Bitmap
            imagen_paso_19.setImageBitmap(imagen19)
        }
        if(requestCode == 239 && resultCode == Activity.RESULT_OK){
            imagen20 = data!!.extras?.get("data") as Bitmap
            imagen_paso_20.setImageBitmap(imagen20)
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
            if(receta.imagen1 != null) imagen_paso_1.setImageBitmap(receta.imagen1)
            if(receta.imagen2 != null) imagen_paso_2.setImageBitmap(receta.imagen2)
            if(receta.imagen3 != null) imagen_paso_3.setImageBitmap(receta.imagen3)
            if(receta.imagen4 != null) imagen_paso_4.setImageBitmap(receta.imagen4)
            if(receta.imagen5 != null) imagen_paso_5.setImageBitmap(receta.imagen5)
            if(receta.imagen6 != null) imagen_paso_6.setImageBitmap(receta.imagen6)
            if(receta.imagen7 != null) imagen_paso_7.setImageBitmap(receta.imagen7)
            if(receta.imagen8 != null) imagen_paso_8.setImageBitmap(receta.imagen8)
            if(receta.imagen9 != null) imagen_paso_9.setImageBitmap(receta.imagen9)
            if(receta.imagen10 != null) imagen_paso_10.setImageBitmap(receta.imagen10)
            if(receta.imagen11 != null) imagen_paso_11.setImageBitmap(receta.imagen11)
            if(receta.imagen12 != null) imagen_paso_12.setImageBitmap(receta.imagen12)
            if(receta.imagen13 != null) imagen_paso_13.setImageBitmap(receta.imagen13)
            if(receta.imagen14 != null) imagen_paso_14.setImageBitmap(receta.imagen14)
            if(receta.imagen15 != null) imagen_paso_15.setImageBitmap(receta.imagen15)
            if(receta.imagen16 != null) imagen_paso_16.setImageBitmap(receta.imagen16)
            if(receta.imagen17 != null) imagen_paso_17.setImageBitmap(receta.imagen17)
            if(receta.imagen18 != null) imagen_paso_18.setImageBitmap(receta.imagen18)
            if(receta.imagen19 != null) imagen_paso_19.setImageBitmap(receta.imagen19)
            if(receta.imagen20 != null) imagen_paso_20.setImageBitmap(receta.imagen20)




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

            //Dificultad
            for(i in 0..(spiner_dificultad.getAdapter().getCount()-1)){
                if(spiner_dificultad.getAdapter().getItem(i).toString().contains(receta.dificultad.toString())){
                    spiner_dificultad.setSelection(i);
                }
            }

            //Thermomix
            check_thermomix.isChecked = receta.thermomix



            num_ingredientes = receta.num_ingredientes
            num_pasos = receta.num_pasos



            boton_editar.setOnClickListener {
                //Imagen
                var imagen:Bitmap
                if(imagen_seleccionada != null){
                    imagen = imagen_seleccionada as Bitmap
                }
                else{
                    imagen = receta.imagen
                }

                if(imagen1 != null) receta.imagen1 = imagen1
                if(imagen2 != null) receta.imagen2 = imagen2
                if(imagen3 != null) receta.imagen3 = imagen3
                if(imagen4 != null) receta.imagen4 = imagen4
                if(imagen5 != null) receta.imagen5 = imagen5
                if(imagen6 != null) receta.imagen6 = imagen6
                if(imagen7 != null) receta.imagen7 = imagen7
                if(imagen8 != null) receta.imagen8 = imagen8
                if(imagen9 != null) receta.imagen9 = imagen9
                if(imagen10 != null) receta.imagen10 = imagen10
                if(imagen11 != null) receta.imagen11 = imagen11
                if(imagen12 != null) receta.imagen12 = imagen12
                if(imagen13 != null) receta.imagen13 = imagen13
                if(imagen14 != null) receta.imagen14 = imagen14
                if(imagen15 != null) receta.imagen15 = imagen15
                if(imagen16 != null) receta.imagen16 = imagen16
                if(imagen17 != null) receta.imagen17 = imagen17
                if(imagen18 != null) receta.imagen18 = imagen18
                if(imagen19 != null) receta.imagen19 = imagen19
                if(imagen20 != null) receta.imagen20 = imagen20

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
                            paso_1.text.toString(),
                            receta.imagen1,
                            paso_2.text.toString(),
                            receta.imagen2,
                            paso_3.text.toString(),
                            receta.imagen3,
                            paso_4.text.toString(),
                            receta.imagen4,
                            paso_5.text.toString(),
                            receta.imagen5,
                            paso_6.text.toString(),
                            receta.imagen6,
                            paso_7.text.toString(),
                            receta.imagen7,
                            paso_8.text.toString(),
                            receta.imagen8,
                            paso_9.text.toString(),
                            receta.imagen9,
                            paso_10.text.toString(),
                            receta.imagen10,
                            paso_11.text.toString(),
                            receta.imagen11,
                            paso_12.text.toString(),
                            receta.imagen12,
                            paso_13.text.toString(),
                            receta.imagen13,
                            paso_14.text.toString(),
                            receta.imagen14,
                            paso_15.text.toString(),
                            receta.imagen15,
                            paso_16.text.toString(),
                            receta.imagen16,
                            paso_17.text.toString(),
                            receta.imagen17,
                            paso_18.text.toString(),
                            receta.imagen18,
                            paso_19.text.toString(),
                            receta.imagen19,
                            paso_20.text.toString(),
                            receta.imagen20,
                            obtenerUsuarioLogueado()[0].username,
                            num_ingredientes,
                            num_pasos,
                            spiner_dificultad.selectedItem.toString(),
                            check_thermomix.isChecked
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
                    var imagen = imagen_seleccionada

                    var db = obtenerBaseDatos(requireContext())

                    db.recetaDao.insertaUna(Receta(0, input_titulo_receta.text.toString(), input_descripcion.text.toString(), "",
                        imagen!!, spiner_categoria.selectedItem.toString(),
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
                            paso_1.text.toString(),
                            imagen1,
                            paso_2.text.toString(),
                            imagen2,
                            paso_3.text.toString(),
                            imagen3,
                            paso_4.text.toString(),
                            imagen4,
                            paso_5.text.toString(),
                            imagen5,
                            paso_6.text.toString(),
                            imagen6,
                            paso_7.text.toString(),
                            imagen7,
                            paso_8.text.toString(),
                            imagen8,
                            paso_9.text.toString(),
                            imagen9,
                            paso_10.text.toString(),
                            imagen10,
                            paso_11.text.toString(),
                            imagen11,
                            paso_12.text.toString(),
                            imagen12,
                            paso_13.text.toString(),
                            imagen13,
                            paso_14.text.toString(),
                            imagen14,
                            paso_15.text.toString(),
                            imagen15,
                            paso_16.text.toString(),
                            imagen16,
                            paso_17.text.toString(),
                            imagen17,
                            paso_18.text.toString(),
                            imagen18,
                            paso_19.text.toString(),
                            imagen19,
                            paso_20.text.toString(),
                            imagen20,
                            obtenerUsuarioLogueado()[0].username,
                            num_ingredientes,
                            num_pasos,
                            spiner_dificultad.selectedItem.toString(),
                            check_thermomix.isChecked
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

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == 101){//permiso de la cámara
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                val lanzador_camara = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(lanzador_camara, 111)
            }
        }
    }

    /*
        Este método se encarga de definir el intent que abrirá la galería así como el código utilizado
        para capturar la respuesta.
     */
    private fun anadirImagenPrincipal(){
        boton_seleccion_imagen.setOnClickListener {

            var builder:AlertDialog.Builder = AlertDialog.Builder(requireContext());
            builder.setTitle("Elija desde donde obtener la imagen")


            builder.setPositiveButton("Galería"){dialogo, _ ->
                dialogo.dismiss()

                var lanzador_seleccion = Intent(Intent.ACTION_PICK)
                lanzador_seleccion.type = "image/*"
                startActivityForResult(lanzador_seleccion, 100)
            }
            builder.setNegativeButton("Cámara"){dialogo, _ ->
                dialogo.dismiss()
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED
                        || checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                        var permisos = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        requestPermissions(permisos, 101)
                    }
                    else{
                        val lanzador_camara = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        startActivityForResult(lanzador_camara, 111)
                    }
                }
                else{
                    val lanzador_camara = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(lanzador_camara, 111)
                }
            }

            var dialogo = builder.create()
            dialogo.show()
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
        inicializaListenersImágenesPasos()
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

        val adapter2 = ArrayAdapter(requireContext(), R.layout.spinner_item, arrayListOf ("Fácil", "Media","Difícil"))
        spiner_dificultad.setAdapter(adapter2)
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
        check_thermomix = view.findViewById(R.id.check_thermomix)
        spiner_dificultad = view.findViewById(R.id.spiner_dificultad)

        imagen_paso_1 = view.findViewById(R.id.imagen_paso_1)
        imagen_paso_2 = view.findViewById(R.id.imagen_paso_2)
        imagen_paso_3 = view.findViewById(R.id.imagen_paso_3)
        imagen_paso_4 = view.findViewById(R.id.imagen_paso_4)
        imagen_paso_5 = view.findViewById(R.id.imagen_paso_5)
        imagen_paso_6 = view.findViewById(R.id.imagen_paso_6)
        imagen_paso_7 = view.findViewById(R.id.imagen_paso_7)
        imagen_paso_8 = view.findViewById(R.id.imagen_paso_8)
        imagen_paso_9 = view.findViewById(R.id.imagen_paso_9)
        imagen_paso_10 = view.findViewById(R.id.imagen_paso_10)
        imagen_paso_11 = view.findViewById(R.id.imagen_paso_11)
        imagen_paso_12 = view.findViewById(R.id.imagen_paso_12)
        imagen_paso_13 = view.findViewById(R.id.imagen_paso_13)
        imagen_paso_14= view.findViewById(R.id.imagen_paso_14)
        imagen_paso_15 = view.findViewById(R.id.imagen_paso_15)
        imagen_paso_16 = view.findViewById(R.id.imagen_paso_16)
        imagen_paso_17 = view.findViewById(R.id.imagen_paso_17)
        imagen_paso_18 = view.findViewById(R.id.imagen_paso_18)
        imagen_paso_19 = view.findViewById(R.id.imagen_paso_19)
        imagen_paso_20 = view.findViewById(R.id.imagen_paso_20)

    }

}