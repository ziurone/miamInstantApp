package com.example.miaminstantapp.extensions

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.WindowManager

fun Activity.setFullScreen() {
    window.apply {
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
        clearFlags(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        statusBarColor = Color.TRANSPARENT
        navigationBarColor = Color.TRANSPARENT
    }
}