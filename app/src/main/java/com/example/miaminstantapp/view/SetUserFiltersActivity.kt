package com.example.miaminstantapp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.miaminstantapp.R
import dagger.android.support.DaggerAppCompatActivity

class SetUserFiltersActivity: DaggerAppCompatActivity() {

    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_set_user_filters)

        navigationController = findNavController(R.id.setUserFiltersFragmentHost)

        val graph = navigationController.navInflater.inflate(R.navigation.set_user_filters_graph)
        navigationController.graph = graph
    }

    companion object {
        private fun getCallingIntent(context: Context): Intent {
            return Intent(context, SetUserFiltersActivity::class.java)
        }

        fun startActivity(context: Context) {
            context.startActivity(getCallingIntent(context))
        }
    }
}