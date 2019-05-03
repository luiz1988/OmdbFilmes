package br.edu.ifsp.scl.omdbfilmes.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.ifsp.scl.omdbfilmes.MainActivity
import br.edu.ifsp.scl.omdbfilmes.Model.MovieOMDB
import br.edu.ifsp.scl.omdbfilmes.R
import br.edu.ifsp.scl.omdbfilmes.Retrofit.OmdbFilmes
import br.edu.ifsp.scl.omdbfilmes.loadPicasso
import kotlinx.android.synthetic.main.fragment_inicial.view.*

class FragmentInicial : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layoutView = inflater.inflate(R.layout.fragment_inicial, null, false)
        val omdbFilmes = OmdbFilmes(activity as MainActivity)
        layoutView.btnBuscar.setOnClickListener {
            omdbFilmes.pesquisarFilme(layoutView.txtBusca_textInputEditText.text.toString())
        }

        omdbFilmes.callback = object : OmdbFilmes.MovieCallback {
            override fun onResponse(obj: MovieOMDB) {
                val url = obj.poster



                if(url != null) {
                    layoutView.ImageMovie.loadPicasso(url)
                    layoutView.valotTituloTextView.setText(obj.title.toString())
                    layoutView.valorAnoTextView.setText(obj.year.toString())
                    layoutView.valorActorsTextView.setText(obj.actors?.replace(",", "\n".toString()))
                }
            }
        }
        return layoutView
    }
}