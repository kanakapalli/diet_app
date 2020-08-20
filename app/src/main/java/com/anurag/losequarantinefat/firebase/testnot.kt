package com.anurag.losequarantinefat.firebase

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import com.androiddevs.firebasenotifications.NotificationData
import com.androiddevs.firebasenotifications.PushNotification
import com.androiddevs.firebasenotifications.RetrofitInstance
import com.anurag.losequarantinefat.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_testnot.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val TOPIC = "/topics/Anurag"
class testnot : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testnot)
        button3.visibility = GONE
        editTextTextPersonName.visibility = GONE
        editTextTextPersonName2.visibility = GONE
        button4.setOnClickListener {
            if(editTextTextPersonName3.text.toString() == "Anurag@1997asdfghjkl;'"){
                button3.visibility = VISIBLE
                editTextTextPersonName.visibility = VISIBLE
                editTextTextPersonName2.visibility = VISIBLE
                editTextTextPersonName3.visibility = GONE
                button4.visibility = GONE
            }
        }
        editTextTextPersonName3.text.toString()
        button3.setOnClickListener {
            val T =editTextTextPersonName.text.toString()
            val B = editTextTextPersonName2.text.toString()
            if(T.isNotEmpty() && B.isNotEmpty() ) {
                PushNotification(NotificationData(T, B),TOPIC).also {
                    sendNotification(it)
                }
            }
        }
    }
}
 fun sendNotification(notification: PushNotification) = CoroutineScope(Dispatchers.IO).launch {
    try {
        val response = RetrofitInstance.api.postNotification(notification)
        if(response.isSuccessful) {
//            Log.d(TAG, "Response: ${Gson().toJson(response)}")
        } else {
//            Log.e(TAG, response.errorBody().toString())
        }
    } catch(e: Exception) {
        Log.e(TAG, e.toString())
    }
}