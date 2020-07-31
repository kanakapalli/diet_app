package com.example.dietapp.repo

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.dietapp.dataclass.breakfast_type
import java.lang.Appendable

class Mainscreen_repo(application: Application) {
    val breakfast_data = MutableLiveData<breakfast_type>()
}