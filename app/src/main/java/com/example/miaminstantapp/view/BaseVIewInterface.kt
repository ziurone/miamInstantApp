package com.example.miaminstantapp.view

import androidx.annotation.LayoutRes
import androidx.lifecycle.LifecycleOwner
import com.example.miaminstantapp.extension.observe
import com.example.miaminstantapp.viewmodel.BaseViewModel

interface BaseViewInterface<S, V> where V : BaseViewModel<S> {

    @LayoutRes
    fun getLayoutId(): Int

    fun getDomainViewModel(): V
    fun onStateChanged(state: S)

    fun initViews() {}
    fun startInitialDomainAction() {}
    fun cleanUp() {}

}


fun <T, S, V> T.initStateObservers() where V : BaseViewModel<S>, T : LifecycleOwner, T : BaseViewInterface<S, V> {
    observe(getDomainViewModel().getState()) { onStateChanged(it) }
}