package com.anurag.losequarantinefat.BroadcastReciver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import android.util.Log

class BroadcastReciver : BroadcastReceiver(){
    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.d("BroadcastReceiver","BroadcastReceiver on recive")
    }

}