package br.edu.ifsp.scl.omdbfilmes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import br.edu.ifsp.scl.omdbfilmes.Fragments.FragmentInicial
import br.edu.ifsp.scl.omdbfilmes.Retrofit.OmdbFilmes
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_inicial.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.app_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val abreFechaToogle: ActionBarDrawerToggle =
            ActionBarDrawerToggle(
                this,
                menuLateralDrawerLayout,
                toolbar,
                R.string.menu_aberto,

                R.string.menu_fechado
            )
        menuLateralDrawerLayout.addDrawerListener(abreFechaToogle)

        abreFechaToogle.syncState()

        menuNavigationView.setNavigationItemSelectedListener { onNavigationItemSelected(it) }

        SetarFragment(FragmentInicial())

        ImageView(this).loadPicasso("poster")

    }

    private fun onNavigationItemSelected(item: MenuItem): Boolean {
        val retorno = when (item.itemId) {
            R.id.sairMenuItem -> {
                finish()
                true
            }
            else -> false
        }
        menuLateralDrawerLayout.closeDrawer(GravityCompat.START)
        return retorno
    }

    private fun SetarFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }


}

fun ImageView.loadPicasso(url: String) {
    Picasso.get().load(url).into(this)
}
