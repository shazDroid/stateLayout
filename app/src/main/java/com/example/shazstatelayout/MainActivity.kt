package com.example.shazstatelayout

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.shazdroid.statelayout.StateLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val stateLayout = findViewById<StateLayout>(R.id.stateLayout)


        findViewById<Button>(R.id.loadingBtn).setOnClickListener {
            stateLayout.showLoadingView()
        }


        findViewById<Button>(R.id.emptyBtn).setOnClickListener {
            stateLayout.showEmptyView()
        }


        findViewById<Button>(R.id.errorBtn).setOnClickListener {
            stateLayout.showErrorView()
        }


        findViewById<Button>(R.id.contentBtn).setOnClickListener {
            stateLayout.showContent()
        }
    }
}