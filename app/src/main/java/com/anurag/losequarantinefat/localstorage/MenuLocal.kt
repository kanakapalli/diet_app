package com.anurag.losequarantinefat.localstorage

//import java.time.LocalDate
import android.content.Context
import android.content.SharedPreferences
import org.threeten.bp.LocalDate


class MenuLocal(context: Context) {

    val menuData: SharedPreferences = context.getSharedPreferences("Menudata", Context.MODE_PRIVATE)


    fun getTodayMenu(): Array<String?> {
        var data = arrayOf<String?>()
        data += menuData.getString("Breakfast", null)
        data += menuData.getString("Breakfast_Details", null)

        data += menuData.getString("Lunch", null)
        data += menuData.getString("Lunch_Details", null)

        data += menuData.getString("EveningSnack", null)
        data += menuData.getString("EveningSnack_Details", null)

        data += menuData.getString("Dinner", null)
        data += menuData.getString("Dinner_Details", null)

        data += menuData.getString("breakfast_photo_url", null)
        data += menuData.getString("afternoon_photo_url", null)
        data += menuData.getString("evening_photo_url", null)
        data += menuData.getString("dinner_photo_url", null)

        data += menuData.getString("breakfast_calories", null)
        data += menuData.getString("afternoon_calories", null)
        data += menuData.getString("evening_calories", null)
        data += menuData.getString("dinner_calories", null)

        data += menuData.getString("breakfast_cal_deatails", null)
        data += menuData.getString("afternoon_cal_details", null)
        data += menuData.getString("evening_cal_details", null)
        data += menuData.getString("dinner_cal_details", null)

//        Log.d("reply data", "${data[0]!!}\n${data[1]!!}\n${data[2]!!}\n")
        return data
    }


    fun saveMenu(
        Breakfast: String,
        Breakfast_Details: String,

        Lunch: String,
        Lunch_Details: String,

        EveningSnack: String,
        EveningSnack_Details: String,

        Dinner: String,
        Dinner_Details: String,

        breakfast_photo_url: String,
        afternoon_photo_url: String,
        evening_photo_url: String,
        dinner_photo_url: String,

        breakfast_calories: String,
        afternoon_calories: String,
        evening_calories: String,
        dinner_calories: String,

        breakfast_cal_deatails: String,
        afternoon_cal_details: String,
        evening_cal_details: String,
        dinner_cal_details: String
    ) {
        val editor = menuData.edit()
        editor.putString("Breakfast", Breakfast)
        editor.putString("Breakfast_Details", Breakfast_Details)

        editor.putString("Lunch", Lunch)
        editor.putString("Lunch_Details", Lunch_Details)

        editor.putString("EveningSnack", EveningSnack)
        editor.putString("EveningSnack_Details", EveningSnack_Details)

        editor.putString("Dinner", Dinner)
        editor.putString("Dinner_Details", Dinner_Details)

        editor.putString("breakfast_photo_url", breakfast_photo_url)
        editor.putString("afternoon_photo_url", afternoon_photo_url)
        editor.putString("evening_photo_url", evening_photo_url)
        editor.putString("dinner_photo_url", dinner_photo_url)

        editor.putString("breakfast_calories", breakfast_calories)
        editor.putString("afternoon_calories", afternoon_calories)
        editor.putString("evening_calories", evening_calories)
        editor.putString("dinner_calories", dinner_calories)

        editor.putString("breakfast_cal_deatails", breakfast_cal_deatails)
        editor.putString("afternoon_cal_details", afternoon_cal_details)
        editor.putString("evening_cal_details", evening_cal_details)
        editor.putString("dinner_cal_details", dinner_cal_details)

        editor.apply()
        editor.commit()
    }

    fun getDate(): LocalDate? {
        val date = menuData.getString("date", null)
        if (date != null) {
            val parsedDate = LocalDate.parse("$date")
            return parsedDate
        }
        return null

    }

    fun savedate(Date: String) {
        val editor = menuData.edit()
        editor.putString("date", Date.toString())
        editor.apply()
        editor.commit()
    }


}