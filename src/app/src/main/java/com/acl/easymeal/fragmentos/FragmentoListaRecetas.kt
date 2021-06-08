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

/*
    La clase FragmentoDescripcionReceta se encarga de dotar de funcionalidad al apartado de la aplicación
    encargado de mostrar los detalles de una receta seleccionada.
 */
/*
    Los atributos de esta clase son:
        -> adapter: Variable que representa el adaptador que nos permitirá pasar de una lista de recetas a una GridView de recetas, de tipo listaRecetasAdapter.
        -> recetas: Lista de recetas que van a ser mostradas, de tipo MutableList<Receta>.
        -> cuadricula_recetas: Cuadrícula en la que se mostrarán todas las recetas, de tipo GridView.
        -> argumentos: A través de esta variable obtendremos si tenemos que marcar alguna categoría ya que venimos de seleccionar una en el fragmento de categorías, de tipo FragmentoListaRecetasArgs.
        -> barra_busqueda: Barra de búsqueda por ingrediente, de tipo SearchView.
        -> spiner_tiempo: Spinner que nos permite seleccionar para la búsqueda recetas un rango de tiempo, de tipo Spinner.
        -> spiner_dificultad: Spinner que nos permite seleccionar para la búsqueda recetas un rango de dificultad, de tipo Spinner.
        -> spiner_categoria: Spinner que nos permite seleccionar para la búsqueda recetas una categoría, de tipo Spinner.
        -> tiempo: Indica si se ha seleccionado o no para buscar por tiempo, de tipo String.
        -> dificultad: Indica si se ha seleccionado o no para buscar por dificultad, de tipo String.
        -> ingredientes: Indica si se ha seleccionado o no para buscar por ingredientes, de tipo String.
        -> categoria: Indica si se ha seleccionado o no para buscar por categoria, de tipo String.
 */
class FragmentoListaRecetas : Fragment(), androidx.appcompat.widget.SearchView.OnQueryTextListener {
    lateinit var adapter:listaRecetasAdapter
    lateinit var recetas:MutableList<Receta>
    lateinit var cuadricula_recetas: GridView
    private val argumentos:FragmentoListaRecetasArgs by navArgs()
    private lateinit var barra_busqueda:androidx.appcompat.widget.SearchView
    private lateinit var spiner_tiempo:Spinner
    private lateinit var spiner_dificultad:Spinner
    private lateinit var spiner_categoria:Spinner
    private var tiempo:String = ""
    private var dificultad:String = ""
    private var ingredientes:String = ""
    private var categoria:String = ""

    /*
        En el método onCreate de un fragmento es llamado durante la creación del mismo.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    /*
        En este método se realizan los siguientes pasos:
            -> 1) Se obtiene la vista.
            -> 2) Se llama al método vinculaVistasAtributos que vincula cada atributo de tipo vista con su correspondiente elemento del layout.
            -> 3) Se llama al método inicializaSpinnersBúsqueda que inicializa lo relacionado con la búsqueda.
            -> 4) Se llama al método inicializaListaRecetas que inicializa el adapter pasándole la lista de recetas para mostrarlas.
            -> 5) Se establece un listener para cuando se pulsa en una receta para ir a detalles.
            -> 6) Se devuelve la vista
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Paso 1
        val view = inflater.inflate(R.layout.fragmento_lista_recetas, container, false)

        // Paso 2
        vinculaVistasAtributos(view)

        // Paso 3
        inicializaSpinnersBúsqueda()

        // Paso 4
        inicializaListaRecetas()

        // Paso 5
        barra_busqueda.setOnQueryTextListener(this)

        cuadricula_recetas.setOnItemClickListener{cuadricula_recetas, _, i,_ ->
            var receta = cuadricula_recetas.getItemAtPosition(i) as Receta

            (activity as MainActivity).fromRecetaToDetalles(receta.id.toString())
        }

        // Paso 6
        return view
    }

    /*
        Este método se encarga de realizar la búsqueda por tiempo e ingredientes
     */
    private fun busquedaTiempo():MutableList<Receta>{
        tiempo = spiner_tiempo.selectedItem.toString()
        var db = obtenerBaseDatos(requireContext())
        var ingredientes_array = ingredientes.split(' ')
        var recetas_devolver:MutableList<Receta>

        when(tiempo){
            "5-15 minutos"-> {
                if(ingredientes != ""){
                    recetas_devolver = db.recetaDao.obtenerPorIngredienteTiempo(ingredientes_array, 0, 15)
                }
                else{
                    recetas_devolver = db.recetaDao.obtenerPorTiempo(0, 15)
                }
            }
            "15-60 minutos"->{
                if(ingredientes != ""){
                    recetas_devolver = db.recetaDao.obtenerPorIngredienteTiempo(ingredientes_array, 16, 60)
                }
                else{
                    recetas_devolver = db.recetaDao.obtenerPorTiempo(16, 60)
                }
            }
            "+60 minutos"->{
                if(ingredientes != ""){
                    recetas_devolver = db.recetaDao.obtenerPorIngredienteTiempo(ingredientes_array, 61, 600)
                }
                else{
                    recetas_devolver = db.recetaDao.obtenerPorTiempo(61, 600)
                }
            }
            else->{
                if(ingredientes != ""){
                    recetas_devolver = db.recetaDao.obtenerPorIngrediente(ingredientes.split(" "))
                }
                else{
                    recetas_devolver = db.recetaDao.obtenerTodas()
                }
            }
        }
        return recetas_devolver

    }

    /*
        Este método se encarga de realizar la búsqueda por dificultad e ingredientes
    */
    private fun busquedaDificultad():MutableList<Receta>{
        dificultad = spiner_dificultad.selectedItem.toString()
        var db = obtenerBaseDatos(requireContext())
        var ingredientes_array = ingredientes.split(' ')
        var recetas_devolver:MutableList<Receta>

        when(dificultad){
            "Fácil"-> {
                if(ingredientes != ""){
                    recetas_devolver = db.recetaDao.obtenerPorIngredienteDificultad(ingredientes_array, "Fácil")
                }
                else{
                    recetas_devolver = db.recetaDao.obtenerPorDificultad("Fácil")
                }
            }
            "Media"->{
                if(ingredientes != ""){
                    recetas_devolver = db.recetaDao.obtenerPorIngredienteDificultad(ingredientes_array, "Media")
                }
                else{
                    recetas_devolver = db.recetaDao.obtenerPorDificultad("Media")
                }
            }
            "Difícil"->{
                if(ingredientes != ""){
                    recetas_devolver = db.recetaDao.obtenerPorIngredienteDificultad(ingredientes_array, "Difícil")
                }
                else{
                    recetas_devolver = db.recetaDao.obtenerPorDificultad("Difícil")
                }
            }
            else->{
                if(ingredientes != ""){
                    recetas_devolver = db.recetaDao.obtenerPorIngrediente(ingredientes.split(" "))
                }
                else{
                    recetas_devolver = db.recetaDao.obtenerTodas()
                }
            }
        }
        return recetas_devolver

    }

    /*
        Este método se encarga de definir el comportamiento de la búsqueda general usándo los
        métodos anteriores.
     */
    private fun busqueda():MutableList<Receta>{
        var db = obtenerBaseDatos(requireContext())

        tiempo = spiner_tiempo.selectedItem.toString()
        dificultad = spiner_dificultad.selectedItem.toString()
        categoria = spiner_categoria.selectedItem.toString()


        if(tiempo != "Todas" && dificultad != "Todas" && categoria != "Todas") { //búsqueda por tiempo, dificultad, categoría
            var recetas1:MutableList<Receta>
            var recetas2 = mutableListOf<Receta>()
            var recetas4:MutableList<Receta>

            if(ingredientes == ""){
                recetas1 = db.recetaDao.obtenerPorDificultad(dificultad)

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
                recetas4 = db.recetaDao.obtenerPorCategoría(categoria)
            }
            else{
                recetas1 = busquedaDificultad()
                recetas2 = busquedaTiempo()
                recetas4 = db.recetaDao.obtenerPorCategoríaIngrediente(ingredientes.split(" "), categoria)
            }

            var recetas3 = mutableListOf<Receta>()

            for(i in recetas1){
                for(j in recetas2){
                    for(k in recetas4) {
                        if (i.id == j.id && j.id == k.id) {
                            recetas3.add(i)
                        }
                    }
                }
            }
            recetas = recetas3
        }
        else{ //busqueda por alguno de los campos como son tiempo, dificultad o categoría
            //########################################################
            if(tiempo != "Todas" && dificultad == "Todas" && categoria == "Todas"){ //busqueda tiempo
                if(ingredientes == ""){
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
                    recetas = busquedaTiempo()
                }

            }
            //########################################################
            if(tiempo == "Todas" && dificultad != "Todas" && categoria == "Todas"){ //busqueda dificultad
                if(ingredientes == ""){
                    recetas = db.recetaDao.obtenerPorDificultad(dificultad)
                }
                else{
                    recetas = busquedaDificultad()
                }

            }

            //########################################################
            if(tiempo == "Todas" && dificultad =="Todas" && categoria != "Todas"){ //busqueda categoria
                if(ingredientes == ""){
                    recetas = db.recetaDao.obtenerPorCategoría(categoria)
                }
                else{
                    recetas = db.recetaDao.obtenerPorCategoríaIngrediente(ingredientes.split(" "), categoria)
                }
            }

            //########################################################
            if(tiempo != "Todas" && dificultad !="Todas" && categoria == "Todas"){ //busqueda tiempo y dificultad
                var recetas1:MutableList<Receta>
                var recetas2:MutableList<Receta>

                if(ingredientes == ""){
                    recetas1 = db.recetaDao.obtenerPorDificultad(dificultad)
                    recetas2 = mutableListOf<Receta>()

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
                }
                else{
                    recetas1 = busquedaDificultad()
                    recetas2 = busquedaTiempo()
                }
                recetas.clear()
                for(i in recetas1){
                    for(j in recetas2){
                        if(i.id == j.id){
                            recetas.add(i)
                        }
                    }
                }
            }

            //########################################################
            if(tiempo != "Todas" && dificultad =="Todas" && categoria != "Todas") { //busqueda tiempo y categoria
                var recetas1:MutableList<Receta>
                var recetas2:MutableList<Receta>

                if(ingredientes == ""){
                    recetas1 = db.recetaDao.obtenerPorCategoría(categoria)
                    recetas2 = mutableListOf<Receta>()

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
                }
                else{
                    recetas1 = busquedaTiempo()
                    recetas2 = db.recetaDao.obtenerPorCategoríaIngrediente(ingredientes.split(" "), categoria)
                }


                recetas.clear()
                for(i in recetas1){
                    for(j in recetas2){
                        if(i.id == j.id){
                            recetas.add(i)
                        }
                    }
                }
            }

            //########################################################
            if(tiempo == "Todas" && dificultad !="Todas" && categoria != "Todas") { //busqueda categoria y dificultad
                var recetas1:MutableList<Receta>
                var recetas2:MutableList<Receta>
                if(ingredientes == ""){
                    recetas1 = db.recetaDao.obtenerPorCategoría(categoria)
                    recetas2 = db.recetaDao.obtenerPorDificultad(dificultad)
                }
                else{
                    recetas1 = db.recetaDao.obtenerPorCategoríaIngrediente(ingredientes.split(" "), categoria)
                    recetas2 = busquedaDificultad()
                }



                recetas.clear()
                for(i in recetas1){
                    for(j in recetas2){
                        if(i.id == j.id){
                            recetas.add(i)
                        }
                    }
                }
            }

            if(tiempo == "Todas" && dificultad =="Todas" && categoria == "Todas") { //todas
                if(ingredientes == ""){
                    recetas = db.recetaDao.obtenerTodas()
                }
                else{
                    recetas = db.recetaDao.obtenerPorIngrediente(ingredientes.split(" "))
                }
            }


        }


        return recetas
    }

    /*
        Este método se encarga de inicializar los spinners de tiempo, dificultad y categoría
     */
    private fun inicializaSpinnersBúsqueda(){

        val adapter1 = ArrayAdapter(requireContext(), R.layout.spinner_item, mutableListOf("Todas", "Fácil", "Media", "Difícil"))
        spiner_dificultad.setAdapter(adapter1)


        val adapter2 = ArrayAdapter(requireContext(), R.layout.spinner_item, mutableListOf("Todas", "5-15 minutos", "15-60 minutos", "+60 minutos"))
        spiner_tiempo.setAdapter(adapter2)

        var lista_categorias = arrayListOf<String>()
        var categorias = obtenerBaseDatos(requireContext()).categoriaDao.obtenerTodas()
        var cont = 1
        lista_categorias.add(0, "Todas")
        for(categoria in categorias){
            lista_categorias.add(cont ,categoria.nombreCategoria)
            cont++
        }
        val adapter3 = ArrayAdapter(requireContext(), R.layout.spinner_item, lista_categorias)
        spiner_categoria.setAdapter(adapter3)


        spiner_tiempo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                adapter = listaRecetasAdapter(busqueda(), requireContext(),"", requireActivity() as MainActivity)
                cuadricula_recetas.adapter = adapter
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        spiner_dificultad.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                adapter = listaRecetasAdapter(busqueda(), requireContext(),"",requireActivity() as MainActivity)
                cuadricula_recetas.adapter = adapter
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        spiner_categoria.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                adapter = listaRecetasAdapter(busqueda(), requireContext(),"",requireActivity() as MainActivity)
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
        spiner_categoria = view.findViewById(R.id.spiner_categoria)
    }

    /*
        Este método se encarga de obtener las recetas de la base de  datos e inicializa el adapter
        para mostrar la lista de recetas.
     */
    private fun inicializaListaRecetas(){

        if(argumentos.Categoria != null) {
            categoria = argumentos.Categoria.toString()
            //Categoria
            for (i in 0..(spiner_categoria.getAdapter().getCount() - 1)) {
                if (spiner_categoria.getAdapter().getItem(i).toString().contains(categoria)) {
                    spiner_categoria.setSelection(i);
                }
            }
        }

        adapter = listaRecetasAdapter(busqueda(), requireContext(),"",requireActivity() as MainActivity)
        cuadricula_recetas.adapter = adapter
    }



    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query == ""){
            ingredientes = ""
        }
        return false
    }

    /*
        Este método se invoca cada vez que el usuario escribe o borra algo en la barra de
        búsqueda, se realiza la búsqueda adecuada y se muestran las recetas que coinciden con
        los criterios de la búsqueda.
     */
    override fun onQueryTextChange(newText: String?): Boolean {
        var recetas:MutableList<Receta>

        ingredientes = newText.toString()

        recetas = busqueda()

        adapter = listaRecetasAdapter(recetas, requireContext(),"",requireActivity() as MainActivity)
        cuadricula_recetas.adapter = adapter
        return false
    }
}