package com.arestory.statelayoutsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.arestory.statelayout.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stateLayout.setLoadingLayout(R.layout.loading_layout)
        stateLayout.setEmptyLayout(R.layout.empty_layout)
        stateLayout.setErrorLayout(R.layout.error_layout)

        stateLayout.showLoading()

        rg.setOnCheckedChangeListener { _, checkedId ->

            when(checkedId){

                R.id.rbLoading ->stateLayout.showLoading()
                R.id.rbEmpty ->stateLayout.showEmpty()
                R.id.rbError ->stateLayout.showError()
                R.id.rbContent ->stateLayout.showContent()
            }

        }
    }
}

