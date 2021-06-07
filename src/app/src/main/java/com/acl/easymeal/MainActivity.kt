package com.acl.easymeal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.acl.easymeal.fragmentos.*
import com.acl.easymeal.modelo.Receta
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inicializaControlNavegacion()
    }

    private fun inicializaControlNavegacion(){
        val menu_inferior = findViewById<BottomNavigationView>(R.id.menu_inferior)
        val controlador_navegacion = findNavController(R.id.fragment2)
        menu_inferior.setupWithNavController(controlador_navegacion)
    }

    public fun onRegisterSelected(){
        findNavController(R.id.fragment2).navigate(FragmentoperfilDirections.actionPerfilMenuItemToFragmentoRegister())
    }

    public fun fromRegisterToLogin(){
        findNavController(R.id.fragment2).navigate(FragmentoRegisterDirections.actionFragmentoRegisterToPerfilMenuItem())
    }

    public fun fromPerfilToAnadirReceta(){
        findNavController(R.id.fragment2).navigate(FragmentoperfilDirections.actionPerfilMenuItemToFragmentoAnadirReceta())
    }

    public fun fromAnadirRecetaToPerfil(){
        findNavController(R.id.fragment2).navigate(FragmentoAnadirRecetaDirections.actionFragmentoAnadirRecetaToPerfilMenuItem())
    }

    public fun fromCategoriasToRecetas(categoria:String){
        findNavController(R.id.fragment2).navigate(FragmentoCategoriasDirections.actionCategoriasMenuItemToRecetasMenuItem(categoria))
    }

    public fun fromRecetaToDetalles(idreceta: String){
        findNavController(R.id.fragment2).navigate(FragmentoListaRecetasDirections.actionRecetasMenuItemToFragmentoDescripcionReceta(idreceta))
    }

    public fun fromPerfilToMostrarReceta(idreceta: String){
        findNavController(R.id.fragment2).navigate(FragmentoperfilDirections.actionPerfilMenuItemToFragmentoDescripcionReceta(idreceta))
    }

    public fun fromPerfilToEditar(idreceta: String){
        findNavController(R.id.fragment2).navigate(FragmentoperfilDirections.actionPerfilMenuItemToFragmentoAnadirReceta("editar", idreceta))
    }
}