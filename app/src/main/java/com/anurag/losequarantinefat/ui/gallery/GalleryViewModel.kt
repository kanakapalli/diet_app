package com.anurag.losequarantinefat.ui.gallery

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.json.JSONArray
import java.io.InputStream

class GalleryViewModel : ViewModel() {

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is gallery Fragment"
//    }
//    val text: LiveData<String> = _text


    private val _titleArray = MutableLiveData<JSONArray>()
    val data: LiveData<JSONArray> = _titleArray

    fun getTips(context: Context?){
        val tipsTitle = mutableListOf<String>()
        val tipsSubject = mutableListOf<String>()
        val inputscrem : InputStream? = context?.assets?.open("tips.json")
        val jsonfile = inputscrem?.bufferedReader().use{ it?.readText() }
        val jsonData = JSONArray(jsonfile)
        Log.i("anurag", "${jsonData}")

        _titleArray.value = jsonData
    }
}