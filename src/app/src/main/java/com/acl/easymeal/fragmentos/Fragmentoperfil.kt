package com.acl.easymeal.fragmentos

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.acl.easymeal.MainActivity
import com.acl.easymeal.R
import com.acl.easymeal.adapters.listaRecetasAdapter
import com.acl.easymeal.modelo.Receta
import com.acl.easymeal.modelo.Usuario
import com.acl.easymeal.modelo.obtenerBaseDatos
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Fragmentoperfil : Fragment() {
    lateinit var adapter:listaRecetasAdapter
    private lateinit var login: RelativeLayout
    private lateinit var boton_registrar: MaterialButton
    private lateinit var input_usuario:EditText
    private lateinit var input_contraseña:EditText
    private lateinit var error_login:TextView
    private lateinit var boton_iniciar_sesion:MaterialButton
    private lateinit var perfil:RelativeLayout
    private lateinit var imagen_usuario:ImageView
    private lateinit var nombre_usuario:TextView
    private lateinit var boton_anadir_receta: FloatingActionButton
    private lateinit var boton_cerrar_sesion:MaterialButton
    private lateinit var mis_recetas_text:TextView

    private lateinit var cuadricula_recetas_perfil:GridView

    private lateinit var mis_recetas:MutableList<Receta>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragmento_perfil, container, false)

        vinculaVistasVariables(view)

        defineComportamientoLoginButton()
        defineComportamientoCerrarSesión()

        defineComportamientoRegister()

        muestraPerfiloLogin()



        return view;
    }


    private fun estableceSliderMisRecetas(){
        //Slider de imágenes
        var db = obtenerBaseDatos(requireContext())
        mis_recetas = db.recetaDao.obtenerPorAutor(obtenerUsuarioLogueado()[0].username)

        //Si no hay imágenes no dejamos el hueco vacío, lo eliminamos
        if(mis_recetas.size == 0){
            cuadricula_recetas_perfil.visibility = View.GONE
            mis_recetas_text.visibility = View.GONE
        }

        cuadricula_recetas_perfil
        adapter = listaRecetasAdapter(mis_recetas, requireContext(), "perfil",requireActivity() as MainActivity)
        cuadricula_recetas_perfil.adapter = adapter


        cuadricula_recetas_perfil.setOnItemClickListener{cuadricula_recetas, _, i,_ ->
            var receta = cuadricula_recetas.getItemAtPosition(i) as Receta
            (activity as MainActivity).fromPerfilToMostrarReceta(receta.id.toString())
        }

    }

    /*
        Este método definirá el comportamiento a realizar cuando se pulse el botón de iniciar sesión.
        Para ello se comprobarán los datos y si son correctos se iniciará sesión y se mostrará el perfil
        del usuario, en caso contrario se mostrará un error.
     */
    private fun defineComportamientoLoginButton(){
        boton_iniciar_sesion.setOnClickListener {
            var usuario_introducido = input_usuario.text.toString()
            var contraseña_introducida = input_contraseña.text.toString()

            if(usuario_introducido == "" || contraseña_introducida == ""){ //Si hay algún campo vacío
                error_login.text = "Por favor rellene ambos campos"
                error_login.visibility = View.VISIBLE
            }
            else{ //ambos campos rellenos
                //buscamos el usuario en la base de datos a ver si existe
                var db = obtenerBaseDatos(requireContext())
                var user_db = db.usuarioDao.obtenerPorNombre(usuario_introducido)

                if(!user_db.isEmpty()){  //El usuario existe en la base de datos
                    if(contraseña_introducida == user_db[0].password){
                        //Almacenamos persistentemente el usuario
                        marcaLoginUsuario(usuario_introducido)

                        //Mostramos la pestaña del usuario
                        muestraPerfiloLogin()


                    }
                    else{ //las contraseñas no coinciden
                        error_login.text = "Usuario o contraseña incorrectos"
                        error_login.visibility = View.VISIBLE
                    }
                }
                else{   //El usuario no existe en la base de datos
                    error_login.text = "Usuario o contraseña incorrectos"
                    error_login.visibility = View.VISIBLE
                }
            }
        }
    }

    /*
        Este mñetodo se encarga de dotar de funcionalidad al botón de registrar. Nos redirige al
        framento que nos permite registrar un nuevo usuario
     */
    private fun defineComportamientoRegister(){
        boton_registrar.setOnClickListener {
            (activity as MainActivity).onRegisterSelected()
        }
    }

    /*
        Este método define el comportamiento del botón de cerrar sesión. Elimina el contenido de
        sharedPreferences y muestra de nuevo el login.
     */
    private fun defineComportamientoCerrarSesión(){
        boton_cerrar_sesion.setOnClickListener {
            var sharedPreferences: SharedPreferences = requireContext().applicationContext.getSharedPreferences("ajustes",0)
            val sharedPreferencesEditor: SharedPreferences.Editor = sharedPreferences.edit()
            sharedPreferencesEditor.clear()
            sharedPreferencesEditor.commit()

            login.visibility = View.VISIBLE
            perfil.visibility = View.GONE
            input_contraseña.text.clear()
            input_usuario.text.clear()

        }
    }

    private fun definirComportamientoAnadirReceta(){
        boton_anadir_receta.setOnClickListener {
            (activity as MainActivity).fromPerfilToAnadirReceta()
        }
    }

    /*
        Este método se encarga de mostrar la pestaña de usuario de acuerdo a los datos del
        usuario logueado.
     */
    private fun muestraContenidosUsuario(){
        var usuario_logueado = obtenerUsuarioLogueado()
        nombre_usuario.text = usuario_logueado[0].username
        imagen_usuario.setImageBitmap(usuario_logueado[0].imagen)
        definirComportamientoAnadirReceta()

    }

    /*
        Este método se encarga de dejar de mostrar el login ya que cuando se llama a este método
        un usuario está ya logueado y muestra el perfil de un determinado usuario
     */
    private fun muestraPerfiloLogin(){
        var usuario_logueado = obtenerUsuarioLogueado()
        if(!usuario_logueado.isEmpty()){    //Si hay un usuario logueado
            login.visibility = View.GONE
            perfil.visibility = View.VISIBLE
            //Añade funcionamiento botón logout


            //Mostramos la pestaña del usuario
            muestraContenidosUsuario()

            estableceSliderMisRecetas()
        }
        else{
            login.visibility = View.VISIBLE
            perfil.visibility = View.GONE
        }

    }

    /*
        Este método se encarga de guardar en sharedPreferences el nombre del usuario que ha iniciado
        sesión con el objetivo de mantener su sesión iniciada. Similar al uso de la variable Session
        en programación web.
     */
    private fun marcaLoginUsuario( username:String){
        var sharedPreferences: SharedPreferences = requireContext().getSharedPreferences("ajustes", 0)
        val sharedPreferencesEditor: SharedPreferences.Editor = sharedPreferences.edit()
        sharedPreferencesEditor.putString("usuario", username)
        sharedPreferencesEditor.commit()
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
        Este método se encarga de vincular cada atributo de la clase con su correspondiente
        elemento del layout.
     */
    private fun vinculaVistasVariables(view:View){
        login = view.findViewById(R.id.login)
        boton_registrar = view.findViewById(R.id.boton_registrar)
        input_usuario = view.findViewById(R.id.input_usuario)
        input_contraseña = view.findViewById(R.id.input_contraseña)
        error_login = view.findViewById(R.id.error_login)
        boton_iniciar_sesion = view.findViewById(R.id.boton_iniciar_sesion)
        perfil = view.findViewById(R.id.perfil)
        imagen_usuario = view.findViewById(R.id.imagen_usuario)
        nombre_usuario = view.findViewById(R.id.nombre_usuario)
        boton_anadir_receta = view.findViewById(R.id.boton_anadir_receta)
        cuadricula_recetas_perfil = view.findViewById(R.id.cuadricula_recetas_perfil)
        boton_cerrar_sesion = view.findViewById(R.id.boton_cerrar_sesion)
        mis_recetas_text = view.findViewById(R.id.mis_recetas_text)
    }
}