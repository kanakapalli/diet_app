package com.example.dietapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dietapp.R
import com.example.dietapp.viewModel.Mainscreen_viewModel
import kotlinx.android.synthetic.main.activity_mainscreen.*
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream
import kotlin.random.Random

class Mainscreen : AppCompatActivity() {

    private lateinit var viewModel: Mainscreen_viewModel
    private lateinit var test2: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainscreen)

        read_json()

        viewModel = ViewModelProvider(this).get(Mainscreen_viewModel::class.java)


        viewModel.breakfast_data.observe(this, Observer {
            breakfasr_tv.text = viewModel.breakfast_data.value?.getString("breakfast_item").toString()

        })

        breakfasr_tv.text = viewModel.breakfast_data.value?.getString("breakfast_item").toString()








        button2.setOnClickListener {
            test(breakfast_deatails)
        }
        lunch_expand_button.setOnClickListener {
            test(lunchDetails_deatails)
        }
        evening_expand_button.setOnClickListener {
            test(eveningDetails_deatails)
        }
        dinner_expand_button.setOnClickListener {
            test(dinnerDetails_deatails)
//            if(!expanded) {
//                dinnerDetails_deatails.visibility = VISIBLE
//                expanded = true
//            } else {
//                dinnerDetails_deatails.visibility = GONE
//                expanded = false
//            }
        }


    }

    fun read_json(){
        try{
            val inputscrem : InputStream = application.assets.open("breakfast.json")
            var jsonfile = inputscrem.bufferedReader().use{it.readText()}
            var jsonData = JSONArray(jsonfile)

            var break_data = jsonData.getJSONObject(Random.nextInt(0, 6))
            breakfasr_tv.text = jsonfile.toString()
            breakfasr_tv.text = "sdfgsdfgasdfsdfsd hfdg hfdgh fghgh dfgh dfgh "

        }catch (e: IOException){

        }
    }

    private fun test(view: LinearLayout?) {
        dinnerDetails_deatails.visibility = GONE
        this.eveningDetails_deatails.visibility = GONE
        breakfast_deatails.visibility = GONE
        lunchDetails_deatails.visibility = GONE

        if (view != null) {
            view.visibility = VISIBLE
        }
    }
}