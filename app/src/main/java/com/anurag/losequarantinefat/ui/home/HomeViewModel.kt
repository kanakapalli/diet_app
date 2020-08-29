package com.anurag.losequarantinefat.ui.home

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anurag.losequarantinefat.localstorage.MenuLocal
import com.anurag.losequarantinefat.localstorage.StorageSP
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.coroutineContext
import kotlin.random.Random

class HomeViewModel : ViewModel() {
    val TAG = "%%%%%%%%%%% HOME VIEWM"
    private val _text = MutableLiveData<String>().apply {
        value = "BURN YOUR QUARANTINE FAT"
    }
    val text: LiveData<String> = _text




    private val title = MutableLiveData<String>().apply {
        value = "Healthy Splash"
    }
    val title_text: LiveData<String> = title

    private val _quote = MutableLiveData<String>().apply {
        val data = listOf<String>("HUSTLE and DEPLOY,","Evolve stronger.","LET YOUR JOURNEY EVOLVE LET IT TAKE TIME:\n LET IT FRAME ITSELF LET IT BE.","Fitness is legal so go do it.","Few blessings take to evolve","Its's always worth trying to get stronger.","Let excuses be the pedals to move on and excel.","Always dive into good things that you crave for","Destiny will prevail in its time,\n Just stick to the journey you though to make","EVOLVE STRONGER","Get stronger and fit each day")
        Log.d(TAG,"${data.size},,,,,")
        value = data[Random.nextInt(0, data.size)]
    }
    val quote: LiveData<String> = _quote


    private val breakfast_data = MutableLiveData<JSONObject>()
    val breakfast_dataL: LiveData<JSONObject> = breakfast_data


    fun Newdata(context: Context?) {
        val menulocal = context?.let { MenuLocal(it) }
        Log.d(TAG, menulocal!!.getDate().toString())
        val inputscrem: InputStream? = context.assets?.open("breakfast.json")
        val jsonfile = inputscrem?.bufferedReader().use { it?.readText() }
        val jsonData = JSONArray(jsonfile).getJSONObject(Random.nextInt(0, 6))
        Log.i("anurag", "${jsonData}")
        breakfast_data.value = jsonData
        menulocal.saveMenu(
            jsonData.getString("breakfast_item").toString(),
            jsonData.getString("breakfast_details").toString(),

            jsonData.getString("afternoon_item").toString(),
            jsonData.getString("afternoon_details").toString(),

            jsonData.getString("evening_item").toString(),
            jsonData.getString("evening_details").toString(),

            jsonData.getString("dinner_item").toString(),
            jsonData.getString("dinner_details").toString(),

            jsonData.getString("breakfast_photo_url").toString(),
            jsonData.getString("afternoon_photo_url").toString(),
            jsonData.getString("evening_photo_url").toString(),
            jsonData.getString("dinner_photo_url").toString(),

            jsonData.getString("breakfast_calories").toString(),
            jsonData.getString("afternoon_calories").toString(),
            jsonData.getString("evening_calories").toString(),
            jsonData.getString("dinner_calories").toString(),

            jsonData.getString("breakfast_cal_deatails").toString(),
            jsonData.getString("afternoon_cal_details").toString(),
            jsonData.getString("evening_cal_details").toString(),
            jsonData.getString("dinner_cal_details").toString()
        )
        val date = SimpleDateFormat("yyyy-MM-dd").format(getDaysAgo(0))
        menulocal.savedate(date)
    }
    private val _menuLocal = MutableLiveData<Array<String?>>()
    val menuLocal: LiveData<Array<String?>> = _menuLocal

    fun applyingData(context: Context?) {
        val menulocal = context?.let { MenuLocal(it) }

        val x = menulocal!!.getTodayMenu()
        for (i in 8..11) Log.d(TAG,"${x[i]}")
        _menuLocal.value = x

    }


    private val BMIsaved = MutableLiveData<String>()
    val BMIsavedL: LiveData<String> = BMIsaved

    fun BMIget(context: Context?) {
        val storageSP = context?.let { StorageSP(it) }
        BMIsaved.value = storageSP!!.getBMI().toString()
        Log.d(TAG, "${BMIsaved.value} asdasdasd")
        Log.d(TAG, "${storageSP.getBMI().toString()} asdasdasd")
    }
    fun getDaysAgo(daysAgo: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, daysAgo)

        return calendar.time
    }


}
