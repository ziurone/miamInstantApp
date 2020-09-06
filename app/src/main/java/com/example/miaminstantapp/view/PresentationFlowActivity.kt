package com.example.miaminstantapp.view

import android.os.Bundle
import com.example.miaminstantapp.R
import com.example.miaminstantapp.view.adapters.PresentationFragmentAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_presentation_flow.*

class PresentationFlowActivity: DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presentation_flow)

        val adapter = PresentationFragmentAdapter(this)
        presentationPager.adapter = adapter
        TabLayoutMediator(presentationTabLayout, presentationPager) { tab, position ->
            tab.text = "PASO " + position.toString()
        }.attach()
    }

}