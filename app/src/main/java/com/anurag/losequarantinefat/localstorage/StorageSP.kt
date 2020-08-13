package com.anurag.losequarantinefat.localstorage

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import kotlin.collections.listOf

class StorageSP(context: Context){

    val Login_data: SharedPreferences  = context.getSharedPreferences("save_loginDATA", Context.MODE_PRIVATE)


    fun getDisplayname(): Array<String?> {
        var data = arrayOf<String?>()
        data += Login_data.getString("getDisplayname",null)
        data += Login_data.getString("getGmailadders",null)
        data += Login_data.getString("getPhotoUrl",null)
        Log.d("reply data", "${data[0]!!}\n${data[1]!!}\n${data[2]!!}\n")
        return data
    }


    fun saveLoginInfo(DisplayName: String,PhotoUrl: String,GmailAdders: String){
        val editor = Login_data.edit()
        editor.putString("getDisplayname", DisplayName)
        editor.putString("getGmailadders", GmailAdders)
        editor.putString("getPhotoUrl", PhotoUrl.toString())
        editor.apply()
        editor.commit()
    }

    fun getBMI(): String?{
        return Login_data.getString("BMI","there is no BMI")

    }

    fun saveBMI(BMI: String){
        val editor = Login_data.edit()
        editor.putString("BMI", BMI.toString())
        editor.apply()
        editor.commit()
    }



}