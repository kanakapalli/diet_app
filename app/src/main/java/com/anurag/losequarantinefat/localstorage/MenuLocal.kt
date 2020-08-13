package com.anurag.losequarantinefat.localstorage

import android.content.Context
import android.content.SharedPreferences
import android.util.Log


class MenuLocal(context: Context){

    val menuData: SharedPreferences = context.getSharedPreferences("Menudata", Context.MODE_PRIVATE)


    fun getTodayMenu(): Array<String?> {
        var data = arrayOf<String?>()
        data += menuData.getString("Breakfast",null)
        data += menuData.getString("Breakfast_Details",null)

        data += menuData.getString("Lunch",null)
        data += menuData.getString("Lunch_Details",null)

        data += menuData.getString("EveningSnack",null)
        data += menuData.getString("EveningSnack_Details",null)

        data += menuData.getString("Dinner",null)
        data += menuData.getString("Dinner_Details",null)

//        Log.d("reply data", "${data[0]!!}\n${data[1]!!}\n${data[2]!!}\n")
        return data
    }


    fun saveMenu(Breakfast: String, Breakfast_Details: String,
                      Lunch: String,Lunch_Details: String,
                      EveningSnack: String,EveningSnack_Details: String,
                      Dinner: String,Dinner_Details: String){
        val editor = menuData.edit()
        editor.putString("Breakfast", Breakfast)
        editor.putString("Breakfast_Details", Breakfast_Details)

        editor.putString("Lunch", Lunch)
        editor.putString("Lunch_Details", Lunch_Details)

        editor.putString("EveningSnack", EveningSnack)
        editor.putString("EveningSnack_Details", EveningSnack_Details)

        editor.putString("Dinner", Dinner)
        editor.putString("Dinner_Details", Dinner_Details)
        editor.apply()
        editor.commit()
    }

    fun getDate(): String?{
        return menuData.getString("date",null)

    }

    fun savedate(Date: String){
        val editor = menuData.edit()
        editor.putString("date", Date.toString())
        editor.apply()
        editor.commit()
    }


}