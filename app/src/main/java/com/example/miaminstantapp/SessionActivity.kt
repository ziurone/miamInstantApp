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

    override fun initViews() {
        super.initViews()
        val navController = Navigation.findNavController(this, R.id.userFiltersFragmentHost)
        NavigationUI.setupWithNavController(bottomAppBar, navController)
        viewModel.listenIngredientsCount()

        viewModel.ingredientsCountLiveData.observe(this) {
            updateDispensaryBadget(it)
        }

    }

    override fun getLayoutId(): Int = R.layout.activity_session

    override fun onStateChanged(state: SessionViewModel.State) {
        when(state) {
            is SessionViewModel.State.DispensaryCounterChange -> Unit
        }
    }

    private fun updateDispensaryBadget(ingredientCount: Int) {
        val badge = bottomAppBar.getOrCreateBadge(R.id.dispensary)
        if(ingredientCount > 0) {
            badge.isVisible = true
            badge.number = ingredientCount
        } else {
            badge.isVisible = false
        }
    }

}
