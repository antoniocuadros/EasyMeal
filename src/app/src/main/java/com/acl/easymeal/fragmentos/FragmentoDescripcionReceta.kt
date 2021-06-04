package com.acl.easymeal.fragmentos

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.acl.easymeal.R
import com.acl.easymeal.adapters.SliderIngredienteAdapter
import com.acl.easymeal.adapters.SliderPasosAdapter
import com.acl.easymeal.adapters.SliderRecetasAdapter
import com.acl.easymeal.modelo.*
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
        Este método devolverá el usuario logueado almacenado en SharedPreferences en caso de que exista,
        en caso contrario devolverá un usuario vacío.
     */
    private fun obtenerUsuarioLogueado():MutableList<Usuario>{
        var sharedPreferences: SharedPreferences = requireContext().applicationContext.getSharedPreferences("ajustes",0)
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

                if(db.valoracionDao.obtenerPorNombreUsuario(user.username, argumentos.receta.toString()).isEmpty()){ //No hay valoraciones previas
                    estrella1.setOnClickListener {
                        db.valoracionDao.insertaUna(Valoracion(0,argumentos.receta.toString(), 1, user.username))
                        muestraValoracion()
                    }
                    estrella2.setOnClickListener {
                        db.valoracionDao.insertaUna(Valoracion(0,argumentos.receta.toString(), 2, user.username))
                        muestraValoracion()
                    }
                    estrella3.setOnClickListener {
                        db.valoracionDao.insertaUna(Valoracion(0,argumentos.receta.toString(), 3, user.username))
                        muestraValoracion()
                    }
                    estrella4.setOnClickListener {
                        db.valoracionDao.insertaUna(Valoracion(0,argumentos.receta.toString(), 4, user.username))
                        muestraValoracion()
                    }
                    estrella5.setOnClickListener {
                        db.valoracionDao.insertaUna(Valoracion(0,argumentos.receta.toString(), 5, user.username))
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
            1->{
                estrellas_vacias.visibility = View.GONE
                estrellas_1.visibility = View.VISIBLE
                estrellas_2.visibility = View.GONE
                estrellas_3.visibility = View.GONE
                estrellas_4.visibility = View.GONE
                estrellas_5.visibility = View.GONE
            }
            2->{
                estrellas_vacias.visibility = View.GONE
                estrellas_1.visibility = View.GONE
                estrellas_2.visibility = View.VISIBLE
                estrellas_3.visibility = View.GONE
                estrellas_4.visibility = View.GONE
                estrellas_5.visibility = View.GONE
            }
            3->{
                estrellas_vacias.visibility = View.GONE
                estrellas_1.visibility = View.GONE
                estrellas_2.visibility = View.GONE
                estrellas_3.visibility = View.VISIBLE
                estrellas_4.visibility = View.GONE
                estrellas_5.visibility = View.GONE
            }
            4->{
                estrellas_vacias.visibility = View.GONE
                estrellas_1.visibility = View.GONE
                estrellas_2.visibility = View.GONE
                estrellas_3.visibility = View.GONE
                estrellas_4.visibility = View.VISIBLE
                estrellas_5.visibility = View.GONE
            }
            5->{
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

        defineComportamientoBotonesPuntuacion()


    }

    /*
     Este método se encarga de inicializar el slider de pasos dada una determinada receta.
     */
    private fun establecePasos(receta:Receta){
        var pasos = mutableListOf<String>()

        if(receta.paso1 != "") pasos.add(receta.paso1)
        if(receta.paso2 != "") pasos.add(receta.paso2)
        if(receta.paso3 != "") pasos.add(receta.paso3)
        if(receta.paso4 != "") pasos.add(receta.paso4)
        if(receta.paso5 != "") pasos.add(receta.paso5)
        if(receta.paso6 != "") pasos.add(receta.paso6)
        if(receta.paso7 != "") pasos.add(receta.paso7)
        if(receta.paso8 != "") pasos.add(receta.paso8)
        if(receta.paso9 != "") pasos.add(receta.paso9)
        if(receta.paso10 != "") pasos.add(receta.paso10)
        if(receta.paso11 != "") pasos.add(receta.paso11)
        if(receta.paso12 != "") pasos.add(receta.paso12)
        if(receta.paso13 != "") pasos.add(receta.paso13)
        if(receta.paso14 != "") pasos.add(receta.paso14)
        if(receta.paso15 != "") pasos.add(receta.paso15)
        if(receta.paso16 != "") pasos.add(receta.paso16)
        if(receta.paso17 != "") pasos.add(receta.paso17)
        if(receta.paso18 != "") pasos.add(receta.paso18)
        if(receta.paso19 != "") pasos.add(receta.paso19)
        if(receta.paso20 != "") pasos.add(receta.paso20)

        slider_pasos_desc.adapter = SliderPasosAdapter(pasos, requireContext())
        slider_pasos_desc.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        indicador_slider_pasos.setViewPager(slider_pasos_desc)
    }

    /*
    Este método se encarga de inicializar el slider de ingredientes dada una determinada receta.
     */
    private fun estableceIngredientes(receta: Receta, db:DataBaseRecetas){
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
    }
}