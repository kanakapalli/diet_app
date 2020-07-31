package com.example.dietapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.dietapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        @Suppress("DEPRECATION")
        Handler().postDelayed({
            startActivity(Intent(this, Mainscreen::class.java))
        },1500)
    }
}