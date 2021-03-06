package com.acl.easymeal.fragmentos

import android.content.SharedPreferences
import android.graphics.Bitmap
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.acl.easymeal.R
import com.acl.easymeal.adapters.SliderIngredienteAdapter
import com.acl.easymeal.adapters.SliderPasosAdapter
import com.acl.easymeal.modelo.*
import me.relex.circleindicator.CircleIndicator3
import java.util.*

/*
    La clase FragmentoDescripcionReceta se encarga de dotar de funcionalidad al apartado de la aplicación
    encargado de mostrar los detalles de una receta seleccionada.
 */
/*
    Los atributos de esta clase son:
        -> imagen_receta_desc: Imagen principal de la receta, de tipo ImageView.
        -> titulo_receta_desc: Nombre de la receta, de tipo TextView.
        -> descripcionReceta: Descrición de la receta, de tipo TextView.
        -> slider_ingredientes_desc: Slider de ingredientes, de tipo ViewPager2.
        -> indicador_slider_ingredientes: Indicador de número de ingrediente actual, de tipo CircleIndicator3.
        -> slider_pasos_desc: Slider de pasos, de tipo ViewPager2.
        -> indicador_slider_pasos: Indicador de número de ingrediente actual, de tipo CircleIndicator3.
        -> nombre_autor: Nombre del autor, de tipo TextView.
        -> argumentos: A través de esta variable obtendremos la receta a mostrar, de tipo FragmentoDescripcionRecetaArgs.
        -> estrella1...estrella5: Imágenes clickables de estrellas, de tipo ImageView.
        -> estrellas_1...estrellas_5: Layouts de conjuntos de estrellas para mostrar más o menos puntuaciones, de tipo LinearLayout.
        -> estrellas_vacias: Layout que muestra las estrellas vacías para poder puntuar, de tipo LinearLayout.
        -> boton_mostrar_pasos: Botón que nos permite mostrar todos los pasos, de tipo ImageButton.
        -> boton_mostrar_ingredientes: Botón que nos permite mostrar todos los ingredientes, de tipo ImageButton.
        -> layout_pasos: Layout de los pasos para poder mostrarlos y ocultarlos, de tipo LinearLayout.
        -> layout_ingredientes: Layout de los ingredientes para poder mostrarlos y ocultarlos, de tipo LinearLayout
        -> reproductor: Reproductor que nos permite escuchar en audio los pasos, de tipo  TextToSpeech.
 */

class FragmentoDescripcionReceta : Fragment(), TextToSpeech.OnInitListener {
    private lateinit var imagen_receta_desc:ImageView
    private lateinit var titulo_receta_desc:TextView
    private lateinit var descripcionReceta:TextView
    private lateinit var slider_ingredientes_desc: ViewPager2
    private lateinit var indicador_slider_ingredientes:CircleIndicator3
    private lateinit var slider_pasos_desc:ViewPager2
    private lateinit var indicador_slider_pasos:CircleIndicator3
    private lateinit var nombre_autor:TextView
    private val argumentos: FragmentoDescripcionRecetaArgs by navArgs()
    private lateinit var estrella1:ImageView
    private lateinit var estrella2:ImageView
    private lateinit var estrella3:ImageView
    private lateinit var estrella4:ImageView
    private lateinit var estrella5:ImageView
    private lateinit var estrellas_1:LinearLayout
    private lateinit var estrellas_2:LinearLayout
    private lateinit var estrellas_3:LinearLayout
    private lateinit var estrellas_4:LinearLayout
    private lateinit var estrellas_5:LinearLayout
    private lateinit var estrellas_vacias:LinearLayout
    private lateinit var boton_mostrar_pasos: ImageButton
    private lateinit var boton_mostrar_ingredientes:ImageButton
    private lateinit var layout_ingredientes:LinearLayout
    private lateinit var layout_pasos:LinearLayout
    private lateinit var layoutTotal:LinearLayout
    private lateinit var reproductor:TextToSpeech

    /*
        En el método onCreate de un fragmento es llamado durante la creación del mismo.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        reproductor = TextToSpeech(requireContext(), this)

    }
/*
    En este método se realizan los siguientes pasos:
        -> 1) Se obtiene la vista.
        -> 2) Se llama al método vinculaVistas que vincula cada atributo de la clase de tipo vista con su elemento en el layout.
        -> 3) Se llama al método inicializaContenidoReceta que dotará de contenido a la vista, mostrando la información de una receta.
        -> 4) Se devuelve la vista.
 */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Paso 1
        var view = inflater.inflate(R.layout.fragmento_descripcion_receta, container, false)

        // Paso 2
        vinculaVistas(view)

        // Paso 3
        inicializaContenidoReceta()

        // Paso 4
        return view
    }

    /*
        Este método devolverá el usuario logueado almacenado en SharedPreferences en caso de que exista,
        en caso contrario devolverá un usuario vacío.
     */
    private fun obtenerUsuarioLogueado():MutableList<Usuario>{
        var sharedPreferences: SharedPreferences = requireContext().applicationContext.getSharedPreferences(
            "ajustes",
            0
        )
        var user = sharedPreferences.getString("usuario", String())

        var db = obtenerBaseDatos(requireContext())
        var user_db = db.usuarioDao.obtenerPorNombre(user.toString())

        return user_db
    }

    /*
        Este método define los listeners para puntuar las recetas
     */
    private fun defineComportamientoBotonesPuntuacion(){
        var user:Usuario
        var db = obtenerBaseDatos(requireContext())


            if(!obtenerUsuarioLogueado().isEmpty()){ //Hay un usuario logueado
                user = obtenerUsuarioLogueado()[0]

                if(db.valoracionDao.obtenerPorNombreUsuario(
                        user.username,
                        argumentos.receta.toString()
                    ).isEmpty()){ //No hay valoraciones previas
                    estrella1.setOnClickListener {
                        db.valoracionDao.insertaUna(
                            Valoracion(
                                0,
                                argumentos.receta.toString(),
                                1,
                                user.username
                            )
                        )
                        muestraValoracion()
                    }
                    estrella2.setOnClickListener {
                        db.valoracionDao.insertaUna(
                            Valoracion(
                                0,
                                argumentos.receta.toString(),
                                2,
                                user.username
                            )
                        )
                        muestraValoracion()
                    }
                    estrella3.setOnClickListener {
                        db.valoracionDao.insertaUna(
                            Valoracion(
                                0,
                                argumentos.receta.toString(),
                                3,
                                user.username
                            )
                        )
                        muestraValoracion()
                    }
                    estrella4.setOnClickListener {
                        db.valoracionDao.insertaUna(
                            Valoracion(
                                0,
                                argumentos.receta.toString(),
                                4,
                                user.username
                            )
                        )
                        muestraValoracion()
                    }
                    estrella5.setOnClickListener {
                        db.valoracionDao.insertaUna(
                            Valoracion(
                                0,
                                argumentos.receta.toString(),
                                5,
                                user.username
                            )
                        )
                        muestraValoracion()
                    }
                }
                else{ //El usuario ya había valorado
                    muestraValoracion()
                }


            }
            else{ //No hay usuarios logueados
                muestraValoracion()
            }


    }

    /*
        Este método se encarga de mostrar la valoración de la receta como media de todas las
        valoraciones de los usuarios.
     */
    private fun muestraValoracion(){
        var db = obtenerBaseDatos(requireContext())
        var valoraciones = db.valoracionDao.obtenerPorNombreReceta(argumentos.receta.toString())
        var suma = 0
        var media:Int

        for(valoracion in valoraciones){
            suma += valoracion.valoracion
        }

        var media_double = (suma*1.0) / valoraciones.size

        media = Math.floor(media_double).toInt()

        when(media){
            1 -> {
                estrellas_vacias.visibility = View.GONE
                estrellas_1.visibility = View.VISIBLE
                estrellas_2.visibility = View.GONE
                estrellas_3.visibility = View.GONE
                estrellas_4.visibility = View.GONE
                estrellas_5.visibility = View.GONE
            }
            2 -> {
                estrellas_vacias.visibility = View.GONE
                estrellas_1.visibility = View.GONE
                estrellas_2.visibility = View.VISIBLE
                estrellas_3.visibility = View.GONE
                estrellas_4.visibility = View.GONE
                estrellas_5.visibility = View.GONE
            }
            3 -> {
                estrellas_vacias.visibility = View.GONE
                estrellas_1.visibility = View.GONE
                estrellas_2.visibility = View.GONE
                estrellas_3.visibility = View.VISIBLE
                estrellas_4.visibility = View.GONE
                estrellas_5.visibility = View.GONE
            }
            4 -> {
                estrellas_vacias.visibility = View.GONE
                estrellas_1.visibility = View.GONE
                estrellas_2.visibility = View.GONE
                estrellas_3.visibility = View.GONE
                estrellas_4.visibility = View.VISIBLE
                estrellas_5.visibility = View.GONE
            }
            5 -> {
                estrellas_vacias.visibility = View.GONE
                estrellas_1.visibility = View.GONE
                estrellas_2.visibility = View.GONE
                estrellas_3.visibility = View.GONE
                estrellas_4.visibility = View.GONE
                estrellas_5.visibility = View.VISIBLE
            }
            else->{
                estrellas_vacias.visibility = View.GONE
                estrellas_1.visibility = View.GONE
                estrellas_2.visibility = View.GONE
                estrellas_3.visibility = View.GONE
                estrellas_4.visibility = View.GONE
                estrellas_5.visibility = View.GONE
            }
        }
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

        establecePasos(receta)

        boton_mostrar_pasos.setOnClickListener {
            if(layout_pasos.visibility == View.GONE){
                layout_pasos.visibility = View.VISIBLE

                val ly = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                ly.gravity = Gravity.TOP
                layoutTotal.setLayoutParams(ly)
            }
            else{
                val ly = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                ly.gravity = Gravity.CENTER
                layoutTotal.setLayoutParams(ly)
                layout_pasos.visibility = View.GONE
            }
        }

        boton_mostrar_ingredientes.setOnClickListener {
            if(layout_ingredientes.visibility == View.GONE){
                layout_ingredientes.visibility = View.VISIBLE

                val ly = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                ly.gravity = Gravity.TOP
                layoutTotal.setLayoutParams(ly)

            }
            else{
                val ly = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                ly.gravity = Gravity.CENTER
                layoutTotal.setLayoutParams(ly)
                layout_ingredientes.visibility = View.GONE
            }
        }

        defineComportamientoBotonesPuntuacion()


    }

    /*
     Este método se encarga de inicializar el slider de pasos dada una determinada receta.
     */
    private fun establecePasos(receta: Receta){
        var pasos = mutableListOf<String>()
        var imgPasos = mutableListOf<Bitmap?>()

        if(receta.paso1 != ""){
            pasos.add(receta.paso1)
            imgPasos.add(receta.imagen1)
        }
        if(receta.paso2 != ""){
            pasos.add(receta.paso2)
            imgPasos.add(receta.imagen2)
        }
        if(receta.paso3 != ""){
            pasos.add(receta.paso3)
            imgPasos.add(receta.imagen3)
        }
        if(receta.paso4 != ""){
            pasos.add(receta.paso4)
            imgPasos.add(receta.imagen4)
        }
        if(receta.paso5 != ""){
            pasos.add(receta.paso5)
            imgPasos.add(receta.imagen5)
        }
        if(receta.paso6 != ""){
            pasos.add(receta.paso6)
            imgPasos.add(receta.imagen6)
        }
        if(receta.paso7 != ""){
            pasos.add(receta.paso7)
            imgPasos.add(receta.imagen7)
        }
        if(receta.paso8 != ""){
            pasos.add(receta.paso8)
            imgPasos.add(receta.imagen8)
        }
        if(receta.paso9 != ""){
            pasos.add(receta.paso9)
            imgPasos.add(receta.imagen9)
        }
        if(receta.paso10 != ""){
            pasos.add(receta.paso10)
            imgPasos.add(receta.imagen10)
        }
        if(receta.paso11 != ""){
            pasos.add(receta.paso11)
            imgPasos.add(receta.imagen11)
        }
        if(receta.paso12 != ""){
            pasos.add(receta.paso12)
            imgPasos.add(receta.imagen12)
        }
        if(receta.paso13 != ""){
            pasos.add(receta.paso13)
            imgPasos.add(receta.imagen13)
        }
        if(receta.paso14 != ""){
            pasos.add(receta.paso14)
            imgPasos.add(receta.imagen14)
        }
        if(receta.paso15 != ""){
            pasos.add(receta.paso15)
            imgPasos.add(receta.imagen15)
        }
        if(receta.paso16 != ""){
            pasos.add(receta.paso16)
            imgPasos.add(receta.imagen16)
        }
        if(receta.paso17 != ""){
            pasos.add(receta.paso17)
            imgPasos.add(receta.imagen17)
        }
        if(receta.paso18 != ""){
            pasos.add(receta.paso18)
            imgPasos.add(receta.imagen18)
        }
        if(receta.paso19 != ""){
            pasos.add(receta.paso19)
            imgPasos.add(receta.imagen19)
        }
        if(receta.paso20 != ""){
            pasos.add(receta.paso20)
            imgPasos.add(receta.imagen20)
        }

        slider_pasos_desc.adapter = SliderPasosAdapter(pasos, imgPasos, requireContext(), reproductor)
        slider_pasos_desc.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        indicador_slider_pasos.setViewPager(slider_pasos_desc)
    }

    /*
    Este método se encarga de inicializar el slider de ingredientes dada una determinada receta.
     */
    private fun estableceIngredientes(receta: Receta, db: DataBaseRecetas){
        //Slider Ingredientes
        var ingredientes = mutableListOf<Ingrediente>()
        var cantidades = mutableListOf<String>()

        if(receta.ingrediente1 != ""){
            var ingrediente = db.ingredienteDao.obtenerPorNombre(receta.ingrediente1)
            if(ingrediente.size == 0){
                ingredientes.add(Ingrediente(receta.ingrediente1, "none"))
            }
            else{
                ingredientes.add(db.ingredienteDao.obtenerPorNombre(receta.ingrediente1)[0])
            }

            cantidades.add(receta.cantidad_ingrediente1)
        }
        if(receta.ingrediente2 != ""){
            var ingrediente = db.ingredienteDao.obtenerPorNombre(receta.ingrediente2)
            if(ingrediente.size == 0){
                ingredientes.add(Ingrediente(receta.ingrediente2, "none"))
            }
            else{
                ingredientes.add(db.ingredienteDao.obtenerPorNombre(receta.ingrediente2)[0])
            }

            cantidades.add(receta.cantidad_ingrediente2)
        }
        if(receta.ingrediente3 != ""){
            var ingrediente = db.ingredienteDao.obtenerPorNombre(receta.ingrediente3)
            if(ingrediente.size == 0){
                ingredientes.add(Ingrediente(receta.ingrediente3, "none"))
            }
            else{
                ingredientes.add(db.ingredienteDao.obtenerPorNombre(receta.ingrediente3)[0])
            }

            cantidades.add(receta.cantidad_ingrediente3)

        }
        if(receta.ingrediente4 != ""){
            var ingrediente = db.ingredienteDao.obtenerPorNombre(receta.ingrediente4)
            if(ingrediente.size == 0){
                ingredientes.add(Ingrediente(receta.ingrediente4, "none"))
            }
            else{
                ingredientes.add(db.ingredienteDao.obtenerPorNombre(receta.ingrediente4)[0])
            }

            cantidades.add(receta.cantidad_ingrediente4)
        }
        if(receta.ingrediente5 != ""){
            var ingrediente = db.ingredienteDao.obtenerPorNombre(receta.ingrediente5)
            if(ingrediente.size == 0){
                ingredientes.add(Ingrediente(receta.ingrediente5, "none"))
            }
            else{
                ingredientes.add(db.ingredienteDao.obtenerPorNombre(receta.ingrediente5)[0])
            }

            cantidades.add(receta.cantidad_ingrediente5)
        }
        if(receta.ingrediente6 != ""){
            var ingrediente = db.ingredienteDao.obtenerPorNombre(receta.ingrediente6)
            if(ingrediente.size == 0){
                ingredientes.add(Ingrediente(receta.ingrediente6, "none"))
            }
            else{
                ingredientes.add(db.ingredienteDao.obtenerPorNombre(receta.ingrediente6)[0])
            }

            cantidades.add(receta.cantidad_ingrediente6)
        }
        if(receta.ingrediente7 != ""){
            var ingrediente = db.ingredienteDao.obtenerPorNombre(receta.ingrediente7)
            if(ingrediente.size == 0){
                ingredientes.add(Ingrediente(receta.ingrediente7, "none"))
            }
            else{
                ingredientes.add(db.ingredienteDao.obtenerPorNombre(receta.ingrediente7)[0])
            }

            cantidades.add(receta.cantidad_ingrediente7)
        }
        if(receta.ingrediente8 != ""){
            var ingrediente = db.ingredienteDao.obtenerPorNombre(receta.ingrediente8)
            if(ingrediente.size == 0){
                ingredientes.add(Ingrediente(receta.ingrediente8, "none"))
            }
            else{
                ingredientes.add(db.ingredienteDao.obtenerPorNombre(receta.ingrediente8)[0])
            }

            cantidades.add(receta.cantidad_ingrediente8)
        }
        if(receta.ingrediente9 != ""){
            var ingrediente = db.ingredienteDao.obtenerPorNombre(receta.ingrediente9)
            if(ingrediente.size == 0){
                ingredientes.add(Ingrediente(receta.ingrediente9, "none"))
            }
            else{
                ingredientes.add(db.ingredienteDao.obtenerPorNombre(receta.ingrediente9)[0])
            }

            cantidades.add(receta.cantidad_ingrediente9)
        }
        if(receta.ingrediente10 != ""){
            var ingrediente = db.ingredienteDao.obtenerPorNombre(receta.ingrediente10)
            if(ingrediente.size == 0){
                ingredientes.add(Ingrediente(receta.ingrediente10, "none"))
            }
            else{
                ingredientes.add(db.ingredienteDao.obtenerPorNombre(receta.ingrediente10)[0])
            }

            cantidades.add(receta.cantidad_ingrediente10)
        }
        if(receta.ingrediente11 != ""){
            var ingrediente = db.ingredienteDao.obtenerPorNombre(receta.ingrediente11)
            if(ingrediente.size == 0){
                ingredientes.add(Ingrediente(receta.ingrediente11, "none"))
            }
            else{
                ingredientes.add(db.ingredienteDao.obtenerPorNombre(receta.ingrediente11)[0])
            }

            cantidades.add(receta.cantidad_ingrediente11)
        }
        if(receta.ingrediente12 != ""){
            var ingrediente = db.ingredienteDao.obtenerPorNombre(receta.ingrediente12)
            if(ingrediente.size == 0){
                ingredientes.add(Ingrediente(receta.ingrediente12, "none"))
            }
            else{
                ingredientes.add(db.ingredienteDao.obtenerPorNombre(receta.ingrediente12)[0])
            }
            cantidades.add(receta.cantidad_ingrediente12)
        }

        slider_ingredientes_desc.adapter = SliderIngredienteAdapter(
            ingredientes,
            cantidades,
            requireContext()
        )
        slider_ingredientes_desc.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        indicador_slider_ingredientes.setViewPager(slider_ingredientes_desc)
    }

    /*
        Este método se encarga de vincular cada atributo de la clase de tipo vista con su correspondiente
        elemento del layout.
     */
    private fun vinculaVistas(view: View){
        imagen_receta_desc = view.findViewById(R.id.imagen_receta_desc)
        titulo_receta_desc = view.findViewById(R.id.titulo_receta_desc)
        descripcionReceta = view.findViewById(R.id.descripcionReceta)
        slider_ingredientes_desc = view.findViewById(R.id.slider_ingredientes_desc)
        indicador_slider_ingredientes = view.findViewById(R.id.indicador_slider_ingredientes)
        slider_pasos_desc = view.findViewById(R.id.slider_pasos_desc)
        indicador_slider_pasos = view.findViewById(R.id.indicador_slider_pasos)
        nombre_autor = view.findViewById(R.id.nombre_autor)
        estrella1 = view.findViewById(R.id.estrella1)
        estrella2 = view.findViewById(R.id.estrella2)
        estrella3 = view.findViewById(R.id.estrella3)
        estrella4 = view.findViewById(R.id.estrella4)
        estrella5 = view.findViewById(R.id.estrella5)

        estrellas_vacias = view.findViewById(R.id.estrellas_vacias)
        estrellas_1 = view.findViewById(R.id.estrellas_1)
        estrellas_2 = view.findViewById(R.id.estrellas_2)
        estrellas_3 = view.findViewById(R.id.estrellas_3)
        estrellas_4 = view.findViewById(R.id.estrellas_4)
        estrellas_5 = view.findViewById(R.id.estrellas_5)

        boton_mostrar_pasos = view.findViewById(R.id.boton_mostrar_pasos)
        boton_mostrar_ingredientes = view.findViewById(R.id.boton_mostrar_ingredientes)
        layout_ingredientes = view.findViewById(R.id.layout_ingredientes)
        layout_pasos = view.findViewById(R.id.layout_pasos)
        layoutTotal = view.findViewById(R.id.layoutTotal)
    }

    /*
        Método que nos permite establecer el reproductor de texto en español.
     */
    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            val spanish = Locale("es", "ES")
            reproductor.setLanguage(spanish)
        }
    }

    /*
        Se ha sobrecargado este método para al cambiar de fragmento parar el reproductor.
     */
    override fun onDestroy() {
        super.onDestroy()
        reproductor.shutdown()
    }
}