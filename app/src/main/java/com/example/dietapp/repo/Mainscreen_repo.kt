package com.example.dietapp.repo

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.dietapp.dataclass.breakfast_type
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import kotlin.random.Random

class Mainscreen_repo(val application: Application) {
    val break_data = MutableLiveData<JSONObject>()
    var jsonfile : String? = null


    fun read_json(){
        try{
            val inputscrem : InputStream = application.assets.open("breakfast.json")
            jsonfile = inputscrem.bufferedReader().use{it.readText()}
            var jsonData = JSONArray(jsonfile)
            Log.i("anurag", "${jsonData}")
            break_data.value = jsonData.getJSONObject(Random.nextInt(0, 6))

        }catch (e: IOException){

        }
    }
}