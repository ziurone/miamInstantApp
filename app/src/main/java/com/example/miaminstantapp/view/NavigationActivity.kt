package com.example.miaminstantapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.miaminstantapp.viewmodel.BaseViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class NavigationActivity<V, S> : BaseViewInterface<S, V>, DaggerAppCompatActivity() where V : BaseViewModel<S>  {
    @Inject
    lateinit var viewModel: V

    override fun getDomainViewModel(): V {
        return viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent?.let { intent ->

        }

        initViews()

        initStateObservers()
    }

}