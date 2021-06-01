package com.acl.easymeal.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acl.easymeal.R
import com.acl.easymeal.modelo.obtenerBaseDatos


class FragmentoListaRecetas : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragmento_lista_recetas, container, false)
        obtenerBaseDatos(requireContext())
        return view
    }
}