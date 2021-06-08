package com.acl.easymeal.fragmentos

import android.Manifest
import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
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
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.acl.easymeal.MainActivity
import com.acl.easymeal.R
import com.acl.easymeal.modelo.Usuario
import com.acl.easymeal.modelo.obtenerBaseDatos
import com.google.android.material.button.MaterialButton

/*
    La clase FragmentoRegister se encarga de dotar de funcionalidad al apartado de la aplicación
    encargado de mostrar el formulario de registro.
 */
/*
    Los atributos de esta clase son:
        -> boton_redirect_login: Botón para redirigir al login, de tipo ImageButton
        -> input_usuario_register: Casilla para rellenar con el usuario, de tipo EditText
        -> input_contraseña_register: Casilla para rellenar con la contraseña, de tipo EditText
        -> input_contraseña_register_repeat: Casilla para rellenar la confirmación de contraseña, te tipo EditText.
        -> boton_register_register: Botón para registrar el usuario, de tipo MaterialButton.
        -> error_register: Texto de error en registro, de tipo TextView.
        -> boton_seleccion_imagen: Botón para seleccionar imagen, de tipo CardView.
        -> imagen_seleccionada: Imagen de perfil seleccionada, de tipo Bitmap.
        -> imagen_usuario_register: Vista de la imagen seleccionada para mostrarla, de tipo ImageView.
        -> texto_bienvenida: Mensaje de bienvenida al crear un usuario, de tipo TextView.
        -> layoutBienvenida: Layout que contiene el mensaje de bienvenida, de tipo LinearLayout.
        -> layoutRegister: Layout que contiene el formulario de registro, de tipo RelativeLayout.
        -> boton_redirect_login_bienvenida: Botón para ir del mensaje de bienvenida al login, de tipo MaterialButton.
 */
@Suppress("DEPRECATION")
class FragmentoRegister : Fragment() {
    private lateinit var boton_redirect_login:ImageButton
    private lateinit var input_usuario_register:EditText
    private lateinit var input_contraseña_register:EditText
    private lateinit var input_contraseña_register_repeat:EditText
    private lateinit var boton_register_register:MaterialButton
    private lateinit var error_register:TextView
    private lateinit var boton_seleccion_imagen: CardView
    private var imagen_seleccionada: Bitmap? = null
    private lateinit var imagen_usuario_register:ImageView
    private lateinit var imagen_registrado:ImageView
    private lateinit var texto_bienvenida:TextView
    private lateinit var layoutBienvenida:LinearLayout
    private lateinit var layoutRegister:RelativeLayout
    private lateinit var boton_redirect_login_bienvenida:MaterialButton

    /*
        En el método onCreate de un fragmento es llamado durante la creación del mismo.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    /*
    En este método se realizan los siguientes pasos:
        -> 1) Se obtiene la vista.
        -> 2) Se llama al método vinculaVistasVariables que vincula cada atributo de tipo vista con su correspondiente elemento del layout.
        -> 3) Se llama al método estableceComportamientoBotonTengoCuenta que establece el comportamiento del boton para ir al login.
        -> 4) Se llama al método estableceComportamientoBotonRegister que define el comportamiento del botón para registrar a un usuario.
        -> 5) Se llama al método definirComportamientoBotonseleccionarImagen que define el comportamiento del botón para añadir imagen de perfil.
        -> 6) Se devuelve la vista
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Paso 1
        val view =  inflater.inflate(R.layout.fragmento_register, container, false)

        // Paso 2
        vinculaVistasVariables(view)

        // Paso 3
        estableceComportamientoBotonTengoCuenta()

        // Paso 4
        estableceComportamientoBotonRegister()

        // Paso 5
        definirComportamientoBotonseleccionarImagen()

        // Paso 6
        return view
    }

    /*
        En este método obtenemos la imagen seleccionada por el usuario y la almacenamos en un atributo
        de la clase.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 100 && resultCode == Activity.RESULT_OK){
            var imagen_seleccionada2 = data!!.data!!
            imagen_seleccionada =  MediaStore.Images.Media.getBitmap(context?.contentResolver, imagen_seleccionada2)
            imagen_usuario_register.setImageBitmap(imagen_seleccionada)
            imagen_usuario_register.visibility = View.VISIBLE

        }
        else{
            if(requestCode == 111 && resultCode == Activity.RESULT_OK){
                imagen_seleccionada = data!!.extras?.get("data") as Bitmap
                imagen_usuario_register.setImageBitmap(imagen_seleccionada)
                imagen_usuario_register.visibility = View.VISIBLE
            }
        }
    }

    /*
        Este método captura el código de los permisos solicitados y en caso de haber otorgado los permisos,
        en este caso, permite abrir la cámara.
     */
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
        Este método se encarga de gestionar el comportamiento del botón de seleccionar imagen,
        creará un intent para abrir la galería.
     */
    private fun definirComportamientoBotonseleccionarImagen(){
        boton_seleccion_imagen.setOnClickListener {
            var builder: AlertDialog.Builder = AlertDialog.Builder(requireContext());
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
                    if(ContextCompat.checkSelfPermission(
                            requireContext(),
                            Manifest.permission.CAMERA
                        ) == PackageManager.PERMISSION_DENIED
                        || ContextCompat.checkSelfPermission(
                            requireContext(),
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ) == PackageManager.PERMISSION_DENIED){
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
        Este método define el comportamiento del botón de register. Comprueba los campos para ver si
        son correctos, en caso contrario muestra errores relacionados y en caso de que sean correctos
        crea un nuevo usuario, lo añade a la base de datos y redirige a la pestaña de login.
     */
    private fun estableceComportamientoBotonRegister(){
        boton_register_register.setOnClickListener {
            var input_user = input_usuario_register.text.toString()
            var input_contraseña = input_contraseña_register.text.toString()
            var input_contraseña_repetida = input_contraseña_register_repeat.text.toString()

            if(input_user == "" || input_contraseña == "" || input_contraseña_repetida == "" ){ //Hay algún campo vacío
                error_register.text = "Por favor rellene todos los campos"
                error_register.visibility = View.VISIBLE
            }
            else{ //Están todos los campos rellenos
                var db = obtenerBaseDatos(requireContext())
                var db_user = db.usuarioDao.obtenerPorNombre(input_user)

                if(!db_user.isEmpty()){ //Ya hay un usuario con dicho username
                    error_register.text = "Nombre de usuario en uso"
                    error_register.visibility = View.VISIBLE
                }
                else{ //Comprobamos que ambas contraseñas intrucidas coincidan
                    if(input_contraseña != input_contraseña_repetida){ //No son iguales
                        error_register.text = "Ambas contraseñas deben coincidir"
                        error_register.visibility = View.VISIBLE
                    }
                    else{
                        var imagen:Bitmap
                        if(imagen_seleccionada != null){ //Si selecciona una imagen la ponemos
                            imagen = imagen_seleccionada as Bitmap
                        }
                        else{ //Si no, una por defecto
                            val drawable: Drawable? = context?.getDrawable(R.drawable.user)

                            imagen = (drawable as BitmapDrawable).bitmap
                        }
                        db.usuarioDao.insertaUna(Usuario(input_user, input_contraseña, imagen))

                        muestraBienvenida(input_user, imagen)

                    }

                }

            }
        }

    }

    /*
        Este método se encarga de una vez un usuario se registra, mostrar una pantalla de
        bienvenida y un botón para ir al apartado login.
     */
    private fun muestraBienvenida(input_user:String, imagen:Bitmap){
        layoutRegister.visibility = View.GONE
        layoutBienvenida.visibility = View.VISIBLE

        imagen_registrado.setImageBitmap(imagen)
        texto_bienvenida.text = "¡Bienvenido " + input_user + "!"

        boton_redirect_login_bienvenida.setOnClickListener {
            (activity as MainActivity).fromRegisterToLogin()
        }
    }

    /*
        Este método se encarga de redirigir al usuario a la pestaña de login cuando el
        usuario seleccione que ya tiene cuenta.
     */
    private fun estableceComportamientoBotonTengoCuenta(){
        boton_redirect_login.setOnClickListener {
            (activity as MainActivity).fromRegisterToLogin()
        }
    }

    /*
        Este método se encarga de vincular cada atributo de la clase con su correspondiente
        elemento del layout.
     */
    private fun vinculaVistasVariables(view:View){
        boton_redirect_login = view.findViewById(R.id.boton_redirect_login)
        input_usuario_register = view.findViewById(R.id.input_usuario_register)
        input_contraseña_register = view.findViewById(R.id.input_contraseña_register)
        input_contraseña_register_repeat = view.findViewById(R.id.input_contraseña_register_repeat)
        boton_register_register = view.findViewById(R.id.boton_register_register)
        error_register = view.findViewById(R.id.error_register)
        boton_seleccion_imagen = view.findViewById(R.id.boton_seleccion_imagen)
        imagen_usuario_register = view.findViewById(R.id.imagen_usuario_register)
        imagen_registrado = view.findViewById(R.id.imagen_registrado)
        texto_bienvenida = view.findViewById(R.id.texto_bienvenida)
        layoutBienvenida = view.findViewById(R.id.layoutBienvenida)
        layoutRegister = view.findViewById(R.id.layoutRegister)
        boton_redirect_login_bienvenida = view.findViewById(R.id.boton_redirect_login_bienvenida)
    }
}