package br.edu.ifsp.scl.omdbfilmes.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.ifsp.scl.omdbfilmes.R

class FragmentInicial: Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val layoutView = inflater.inflate(R.layout.fragment_inicial, null)

        return layoutView
    }

}