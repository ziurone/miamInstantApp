package com.example.miaminstantapp.view.dialogs

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import javax.inject.Inject

class MessageDialogHandler @Inject constructor(): LifecycleObserver {

    private var alertDialog: AlertDialog? = null

    fun show(
        fragment: Fragment,
        cancelable: Boolean = true,
        primaryAction: () -> Unit,
        secondaryAction: () -> Unit,
        content: String,
        primaryButtonText: String,
        secondayButtonText: String
    ) {
        fragment.context?.let {
            fragment.viewLifecycleOwner.lifecycle.addObserver(this)
            hide()
            val builder = AlertDialog.Builder(it)
                .setMessage(content)
                .setCancelable(cancelable)
                .setPositiveButton(primaryButtonText) { _, _ -> primaryAction() }
                .setNegativeButton(secondayButtonText) { _, _ -> secondaryAction() }
            alertDialog = builder.show()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun hide() = alertDialog?.dismiss()
}