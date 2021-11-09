package com.example.miaminstantapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_session.*

class SessionActivity : DaggerAppCompatActivity() {

    companion object {
        private fun getCallingIntent(context: Context): Intent {
            return Intent(context, SessionActivity::class.java)
        }

        fun startActivity(context: Context) {
            context.startActivity(getCallingIntent(context))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_session)

        val navController = Navigation.findNavController(this, R.id.userFiltersFragmentHost)
        NavigationUI.setupWithNavController(bottomAppBar, navController)
    }
}
