package com.example.dietapp.viewModel

import android.R
import android.app.Activity
import android.app.Application
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.dietapp.dataclass.breakfast_type
import com.example.dietapp.repo.Mainscreen_repo
import com.example.dietapp.ui.Mainscreen


class Mainscreen_viewModel(application: Application): AndroidViewModel(application) {
    private val repo = Mainscreen_repo(application)

    val breakfast_data : LiveData<breakfast_type>
    init {
        this.breakfast_data = repo.breakfast_data
    }




}