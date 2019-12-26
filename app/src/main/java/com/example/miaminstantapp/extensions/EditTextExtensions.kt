package com.example.miaminstantapp.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

fun EditText.afterDelayedTextChanged(afterTextChangedAction: (CharSequence) -> Unit, timeout: Long = 750, unit: TimeUnit = TimeUnit.MILLISECONDS) {
    addTextChangedListener(object : TextWatcher {

        val subject: PublishSubject<CharSequence> = PublishSubject.create()

        init {
            subject.debounce(timeout, unit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(afterTextChangedAction, {})
        }


        override fun afterTextChanged(s: Editable?) {
            subject.onNext(s ?: "")
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}