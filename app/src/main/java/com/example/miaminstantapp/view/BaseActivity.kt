package com.example.miaminstantapp.view

import android.os.Bundle
import com.example.miaminstantapp.viewmodel.BaseViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<V, S> : BaseViewInterface<S, V>, DaggerAppCompatActivity() where V : BaseViewModel<S> {

    @Inject
    lateinit var viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initStateObservers()
        setContentView(getLayoutId())
        initViews()
        startInitialDomainAction()
    }

    override fun getDomainViewModel(): V {
        return viewModel
    }
}