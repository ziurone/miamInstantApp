package com.example.miaminstantapp.view

import android.os.Bundle
import android.os.PersistableBundle
import com.example.miaminstantapp.viewmodel.BaseViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<V, S> : BaseViewInterface<S, V>, DaggerAppCompatActivity() where V : BaseViewModel<S> {

    @Inject
    lateinit var viewModel: V

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        initStateObservers()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
    }

    override fun getDomainViewModel(): V {
        return viewModel
    }
}