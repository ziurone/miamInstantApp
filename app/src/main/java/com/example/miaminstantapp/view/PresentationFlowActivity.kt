package com.example.miaminstantapp.view

import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.miaminstantapp.R
import com.example.miaminstantapp.view.adapters.PresentationFragmentAdapter
import com.google.android.material.tabs.TabLayout
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
            tab?.icon = ContextCompat.getDrawable(baseContext, R.drawable.ic_inactive_dot)
            presentationTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab?) {
                    tab?.icon = ContextCompat.getDrawable(baseContext , R.drawable.ic_active_dot)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    tab?.icon = ContextCompat.getDrawable(baseContext, R.drawable.ic_inactive_dot)
                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.icon = ContextCompat.getDrawable(baseContext , R.drawable.ic_active_dot)
                }

            })
        }.attach()
    }

}