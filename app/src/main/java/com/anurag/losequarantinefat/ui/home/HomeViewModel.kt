package com.anurag.losequarantinefat.ui.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anurag.losequarantinefat.localstorage.MenuLocal
import com.anurag.losequarantinefat.localstorage.StorageSP
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStream
import kotlin.random.Random

class HomeViewModel : ViewModel() {
    val TAG = "%%%%%%%%%%% HOME VIEWM"
    private val _text = MutableLiveData<String>().apply {
        value = "BURN YOUR QUARANTINE FAT"
    }
    val text: LiveData<String> = _text


    private val title = MutableLiveData<String>().apply {
        value = "QFAT"
    }
    val title_text: LiveData<String> = title


    private val breakfast_data = MutableLiveData<JSONObject>()
    val breakfast_dataL: LiveData<JSONObject> = breakfast_data

    fun funnction(context: Context?) {
        val menulocal = context?.let { MenuLocal(it) }

//        menulocal!!.savedate("asd")

        val inputscrem: InputStream? = context?.assets?.open("breakfast.json")
        val jsonfile = inputscrem?.bufferedReader().use { it?.readText() }
        val jsonData = JSONArray(jsonfile).getJSONObject(Random.nextInt(0, 6))
        Log.i("anurag", "${jsonData}")
        breakfast_data.value = jsonData
        menulocal!!.saveMenu(jsonData.getString("breakfast_item").toString(),
            jsonData.getString("breakfast_details").toString(),

            jsonData.getString("afternoon_item").toString(),
            jsonData.getString("afternoon_details").toString(),

            jsonData.getString("evening_item").toString(),
            jsonData.getString("evening_details").toString(),

            jsonData.getString("dinner_item").toString(),
            jsonData.getString("dinner_details").toString())
    }

    fun funnction2(context: Context?) {
        val menulocal = context?.let { MenuLocal(it) }

        val x = menulocal!!.getTodayMenu()
        for (i in 0..x.size-1) Log.d(TAG,"${x[i]}")
//        val inputscrem: InputStream? = context?.assets?.open("breakfast.json")
//        val jsonfile = inputscrem?.bufferedReader().use { it?.readText() }
//        var jsonData = JSONArray(jsonfile).getJSONObject(Random.nextInt(0, 6))
//        Log.i("anurag", "${jsonData}")
//        breakfast_data.value = jsonData
//        menulocal.saveMenu(jsonData.getString("breakfast_item").toString(),
//            jsonData.getString("breakfast_details").toString(),
//
//            jsonData.getString("afternoon_item").toString(),
//            jsonData.getString("afternoon_details").toString(),
//
//            jsonData.getString("evening_item").toString(),
//            jsonData.getString("evening_details").toString(),
//
//            jsonData.getString("dinner_item").toString(),
//            jsonData.getString("dinner_details").toString())
    }


    private val BMIsaved = MutableLiveData<String>()
    val BMIsavedL: LiveData<String> = BMIsaved

    fun BMIget(context: Context?) {
        val storageSP = context?.let { StorageSP(it) }
        BMIsaved.value = storageSP!!.getBMI().toString()
        Log.d(TAG, "${BMIsaved.value} asdasdasd")
        Log.d(TAG, "${storageSP.getBMI().toString()} asdasdasd")
    }


}
