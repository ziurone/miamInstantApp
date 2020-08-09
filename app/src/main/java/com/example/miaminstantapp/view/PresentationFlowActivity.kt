package com.example.miaminstantapp.view

import android.os.Bundle
import com.example.miaminstantapp.R
import dagger.android.support.DaggerAppCompatActivity

class PresentationFlowActivity: DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presentation_flow)
    }

}