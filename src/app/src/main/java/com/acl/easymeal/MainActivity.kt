package com.acl.easymeal

import android.database.CursorWindow
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.acl.easymeal.fragmentos.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.reflect.Field


/*
    Esta clase representa la actividad principal, su unica funcionalidad es definir la navegación entre
    fragmentos, ya que el resto de comportamientos están definidos a lo largo de los diversos fragmentos.
 */
class MainActivity : AppCompatActivity() {
    /*
        Este método se llama cuando se está creando la actividad principal. Se llama al método
        inicializaControlNavegacion que inicializa el menú inferior.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            val field: Field = CursorWindow::class.java.getDeclaredField("sCursorWindowSize")
            field.setAccessible(true)
            field.set(null, 100 * 1024 * 1024) //the 100MB is the new size
        } catch (e: Exception) {
        }

        inicializaControlNavegacion()
    }

    /*
        Este método inicializa el menú inferior para navegar entre los distintos fragmentos.
     */
    private fun inicializaControlNavegacion(){
        val menu_inferior = findViewById<BottomNavigationView>(R.id.menu_inferior)
        val controlador_navegacion = findNavController(R.id.fragment2)
        menu_inferior.setupWithNavController(controlador_navegacion)
    }

    /*
        Este método será utilizado para navegar del fragmento de Perfil al de Registro.
     */
    public fun onRegisterSelected(){
        findNavController(R.id.fragment2).navigate(FragmentoperfilDirections.actionPerfilMenuItemToFragmentoRegister())
    }

    /*
        Este método será utilizado para navegar del fragmento de Registro al de Perfil.
     */
    public fun fromRegisterToLogin(){
        findNavController(R.id.fragment2).navigate(FragmentoRegisterDirections.actionFragmentoRegisterToPerfilMenuItem())
    }

    /*
        Este método será utilizado para navegar del fragmento de Perfil al de Añadir receta.
     */
    public fun fromPerfilToAnadirReceta(){
        findNavController(R.id.fragment2).navigate(FragmentoperfilDirections.actionPerfilMenuItemToFragmentoAnadirReceta())
    }

    /*
        Este método será utilizado para navegar del fragmento de Añadir receta al de Perfil.
     */
    public fun fromAnadirRecetaToPerfil(){
        findNavController(R.id.fragment2).navigate(FragmentoAnadirRecetaDirections.actionFragmentoAnadirRecetaToPerfilMenuItem())
    }

    /*
        Este método será utilizado para navegar del fragmento de Categorías al de Recetas.
     */
    public fun fromCategoriasToRecetas(categoria: String){
        findNavController(R.id.fragment2).navigate(FragmentoCategoriasDirections.actionCategoriasMenuItemToRecetasMenuItem(categoria))
    }

    /*
        Este método será utilizado para navegar del fragmento de Recetas al de Detalles de una receta.
     */
    public fun fromRecetaToDetalles(idreceta: String){
        findNavController(R.id.fragment2).navigate(FragmentoListaRecetasDirections.actionRecetasMenuItemToFragmentoDescripcionReceta(idreceta))
    }

    /*
        Este método será utilizado para navegar del fragmento de Perfil al de Detalles de una receta.
     */
    public fun fromPerfilToMostrarReceta(idreceta: String){
        findNavController(R.id.fragment2).navigate(FragmentoperfilDirections.actionPerfilMenuItemToFragmentoDescripcionReceta(idreceta))
    }

    /*
        Este método será utilizado para navegar del fragmento de Perfil al de Editar una receta.
     */
    public fun fromPerfilToEditar(idreceta: String){
        findNavController(R.id.fragment2).navigate(FragmentoperfilDirections.actionPerfilMenuItemToFragmentoAnadirReceta("editar", idreceta))
    }
}