package br.edu.ifsp.scl.omdbfilmes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import br.edu.ifsp.scl.omdbfilmes.Fragments.FragmentInicial
import kotlinx.android.synthetic.main.activity_main.*
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

    }

    private fun onNavigationItemSelected(item: MenuItem): Boolean {
        var retorno: Boolean = false
        when (item.itemId) {
            R.id.sairMenuItem -> {
                finish()
                retorno = true
            }
        }
        menuLateralDrawerLayout.closeDrawer(GravityCompat.START)
        return retorno
    }

    private fun SetarFragment(fragment: Fragment) {
        val fragmentTarget = fragment;
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragmentTarget)
        fragmentTransaction.commit()
    }
}
