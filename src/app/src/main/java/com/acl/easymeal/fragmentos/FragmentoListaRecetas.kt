package com.acl.easymeal.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.navArgs
import com.acl.easymeal.MainActivity
import com.acl.easymeal.R
import com.acl.easymeal.adapters.listaRecetasAdapter
import com.acl.easymeal.modelo.Categoria
import com.acl.easymeal.modelo.Receta
import com.acl.easymeal.modelo.obtenerBaseDatos


class FragmentoListaRecetas : Fragment(), androidx.appcompat.widget.SearchView.OnQueryTextListener {
    lateinit var adapter:listaRecetasAdapter
    lateinit var recetas:MutableList<Receta>
    lateinit var cuadricula_recetas: GridView
    private val argumentos:FragmentoListaRecetasArgs by navArgs()
    private lateinit var barra_busqueda:androidx.appcompat.widget.SearchView
    private lateinit var spiner_tiempo:Spinner
    private lateinit var spiner_dificultad:Spinner
    private var tiempo:String = ""
    private var dificultad:String = ""
    private var ingredientes:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragmento_lista_recetas, container, false)

        vinculaVistasAtributos(view)

        inicializaListaRecetas()

        inicializaSpinnersBúsqueda()

        barra_busqueda.setOnQueryTextListener(this)

        cuadricula_recetas.setOnItemClickListener{cuadricula_recetas, _, i,_ ->
            var receta = cuadricula_recetas.getItemAtPosition(i) as Receta

            (activity as MainActivity).fromRecetaToDetalles(receta.id.toString())
        }

        return view
    }

    /*
        Este método se encarga de realizar la búsqueda por tiempo e ingredientes
     */
    private fun busquedaTiempo():MutableList<Receta>{
        tiempo = spiner_tiempo.selectedItem.toString()
        var db = obtenerBaseDatos(requireContext())
        var ingredientes_array = ingredientes.split(' ')

        when(tiempo){
            "5-15 minutos"-> {
                if(ingredientes != ""){
                    recetas = db.recetaDao.obtenerPorIngredienteTiempo(ingredientes_array, 0, 15)
                }
                else{
                    recetas = db.recetaDao.obtenerPorTiempo(0, 15)
                }
            }
            "15-60 minutos"->{
                if(ingredientes != ""){
                    recetas = db.recetaDao.obtenerPorIngredienteTiempo(ingredientes_array, 16, 60)
                }
                else{
                    recetas = db.recetaDao.obtenerPorTiempo(16, 60)
                }
            }
            "+60 minutos"->{
                if(ingredientes != ""){
                    recetas = db.recetaDao.obtenerPorIngredienteTiempo(ingredientes_array, 61, 600)
                }
                else{
                    recetas = db.recetaDao.obtenerPorTiempo(61, 600)
                }
            }
            else->{
                if(ingredientes != ""){
                    recetas = db.recetaDao.obtenerPorIngrediente(ingredientes.split(" "))
                }
                else{
                    recetas = db.recetaDao.obtenerTodas()
                }
            }
        }
        return recetas

    }

    /*
        Este método se encarga de realizar la búsqueda por dificultad e ingredientes
    */
    private fun busquedaDificultad():MutableList<Receta>{
        dificultad = spiner_dificultad.selectedItem.toString()
        var db = obtenerBaseDatos(requireContext())
        var ingredientes_array = ingredientes.split(' ')


        when(dificultad){
            "Fácil"-> {
                if(ingredientes != ""){
                    recetas = db.recetaDao.obtenerPorIngredienteDificultad(ingredientes_array, "Fácil")
                }
                else{
                    recetas = db.recetaDao.obtenerPorDificultad("Fácil")
                }
            }
            "Media"->{
                if(ingredientes != ""){
                    recetas = db.recetaDao.obtenerPorIngredienteDificultad(ingredientes_array, "Media")
                }
                else{
                    recetas = db.recetaDao.obtenerPorDificultad("Media")
                }
            }
            "Difícil"->{
                if(ingredientes != ""){
                    recetas = db.recetaDao.obtenerPorIngredienteDificultad(ingredientes_array, "Difícil")
                }
                else{
                    recetas = db.recetaDao.obtenerPorDificultad("Difícil")
                }
            }
            else->{
                if(ingredientes != ""){
                    recetas = db.recetaDao.obtenerPorIngrediente(ingredientes.split(" "))
                }
                else{
                    recetas = db.recetaDao.obtenerTodas()
                }
            }
        }
        return recetas

    }

    /*
        Este método se encarga de definir el comportamiento de la búsqueda
     */
    private fun busqueda():MutableList<Receta>{
        var db = obtenerBaseDatos(requireContext())

        tiempo = spiner_tiempo.selectedItem.toString()
        dificultad = spiner_dificultad.selectedItem.toString()

        if(ingredientes == ""){ //No hay ingredientes en la búsqueda
            if(tiempo != "Todas" && dificultad != "Todas") { //búsqueda por tiempo y dificultad
                var recetas1 = db.recetaDao.obtenerPorDificultad(dificultad)

                var recetas2 = mutableListOf<Receta>()

                when(tiempo){
                    "5-15 minutos"-> {
                        recetas2 = db.recetaDao.obtenerPorTiempo(0, 15)

                    }
                    "15-60 minutos"->{
                        recetas2 = db.recetaDao.obtenerPorTiempo(16, 60)
                    }
                    "+60 minutos"->{
                        recetas2 = db.recetaDao.obtenerPorTiempo(61, 600)
                    }
                }


                var recetas3 = mutableListOf<Receta>()

                for(i in recetas1){
                    for(j in recetas2){
                        if(i.id == j.id){
                            recetas3.add(i)
                        }
                    }
                }
                recetas = recetas3
            }
            else{
                if(tiempo != "Todas" && dificultad == "Todas"){ //busqueda tiempo
                    when(tiempo){
                        "5-15 minutos"-> {
                            recetas = db.recetaDao.obtenerPorTiempo(0, 15)

                        }
                        "15-60 minutos"->{
                            recetas = db.recetaDao.obtenerPorTiempo(16, 60)
                        }
                        "+60 minutos"->{
                            recetas = db.recetaDao.obtenerPorTiempo(61, 600)
                        }
                    }
                }
                else{
                    if(tiempo == "Todas" && dificultad != "Todas"){ //busqueda dificultad
                        recetas = db.recetaDao.obtenerPorDificultad(dificultad)
                    }
                    else{ //todas
                        recetas = db.recetaDao.obtenerTodas()
                    }
                }
            }
        }
        else{  //Hay ingredientes en la búsqueda
            var ingredientes_array = ingredientes.split(' ')

            if(tiempo != "Todas" && dificultad != "Todas"){ //búsqueda por ingrediente, tiempo y dificultad
                var recetas1 = busquedaTiempo()
                var recetas2 = busquedaDificultad()
                var recetas3 = mutableListOf<Receta>()

                for(i in recetas1){
                    for(j in recetas2){
                        if(i.id == j.id){
                            recetas3.add(i)
                        }
                    }
                }
                recetas = recetas3


            }
            else{
                if(tiempo != "Todas" && dificultad == "Todas"){ //busqueda por ingrediente y tiempo
                    recetas = busquedaTiempo()
                }
                else{
                    if(tiempo == "Todas" && dificultad != "Todas"){
                        recetas = busquedaDificultad()
                    }
                    else{
                        recetas = db.recetaDao.obtenerPorIngrediente(ingredientes_array!!)
                    }

                }
            }


        }
        return recetas
    }

    /*
        Este método se encarga de inicializar los spinners de tiempo y dificultad
     */
    private fun inicializaSpinnersBúsqueda(){

        val adapter1 = ArrayAdapter(requireContext(), R.layout.spinner_item, mutableListOf("Todas", "Fácil", "Media", "Difícil"))
        spiner_dificultad.setAdapter(adapter1)


        val adapter2 = ArrayAdapter(requireContext(), R.layout.spinner_item, mutableListOf("Todas", "5-15 minutos", "15-60 minutos", "+60 minutos"))
        spiner_tiempo.setAdapter(adapter2)


        spiner_tiempo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                adapter = listaRecetasAdapter(busqueda(), requireContext())
                cuadricula_recetas.adapter = adapter
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        spiner_dificultad.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                adapter = listaRecetasAdapter(busqueda(), requireContext())
                cuadricula_recetas.adapter = adapter
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    /*
        Este método se encarga de vincular cada atributo de la clase de tipo vista con su elemento
        correspondiente en el layout.
     */
    private fun vinculaVistasAtributos(view:View){
        cuadricula_recetas = view.findViewById(R.id.cuadricula_recetas)
        barra_busqueda = view.findViewById(R.id.barra_busqueda)
        spiner_tiempo = view.findViewById(R.id.spiner_tiempo)
        spiner_dificultad = view.findViewById(R.id.spiner_dificultad)
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
                if(ingredientes != ""){
                    recetas = db.recetaDao.obtenerPorIngrediente(ingredientes.split(" "))
                }
                else{
                    recetas = db.recetaDao.obtenerTodas()
                }
            }
        }


        adapter = listaRecetasAdapter(recetas, requireContext())
        cuadricula_recetas.adapter = adapter
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query == ""){
            ingredientes = ""
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        var recetas:MutableList<Receta> = mutableListOf()

        ingredientes = newText.toString()

        recetas = busqueda()

        adapter = listaRecetasAdapter(recetas, requireContext())
        cuadricula_recetas.adapter = adapter
        return false
    }
}