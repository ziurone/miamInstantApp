package com.example.miaminstantapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.miaminstantapp.view.BaseActivity
import com.example.miaminstantapp.viewmodel.SessionViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_session.*

class SessionActivity : BaseActivity<SessionViewModel, SessionViewModel.State>() {

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

        val navController = Navigation.findNavController(this, R.id.userFiltersFragmentHost)
        NavigationUI.setupWithNavController(bottomAppBar, navController)
    }

    override fun getLayoutId(): Int = R.layout.activity_session

    override fun onStateChanged(state: SessionViewModel.State) {
        TODO("Not yet implemented")
    }
}
