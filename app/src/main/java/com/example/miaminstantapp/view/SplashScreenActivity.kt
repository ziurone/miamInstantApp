package com.example.miaminstantapp.view

import android.content.Intent
import android.os.Bundle
import com.example.miaminstantapp.SessionActivity
import com.example.miaminstantapp.R
import com.example.miaminstantapp.viewmodel.ISplashScreenViewModel

class SplashScreenActivity: NavigationActivity<ISplashScreenViewModel, ISplashScreenViewModel.State>() {

    override fun getLayoutId(): Int = R.layout.activity_splash_screen

    override fun initViews() {
        viewModel.hasUserUsedAppBefore()
        super.initViews()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
    }

    override fun onStateChanged(state: ISplashScreenViewModel.State) {
        when (state) {
            ISplashScreenViewModel.State.UserHasAlreadyUseTheApp -> navigateToUserIngredients()
            ISplashScreenViewModel.State.IsUserFirstTimeInApp -> navigateToPresentationFlow()
        }
        finish()
    }

    private fun navigateToUserIngredients() {
        this.startActivity(Intent(this, SessionActivity::class.java))
    }

    private fun navigateToPresentationFlow() {
        this.startActivity(Intent(this, PresentationFlowActivity::class.java))
    }
}