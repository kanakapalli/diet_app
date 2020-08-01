package com.example.dietapp.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.dietapp.repo.Mainscreen_repo
import org.json.JSONObject


class Mainscreen_viewModel(application: Application) : AndroidViewModel(application) {
    private val repo = Mainscreen_repo(application)

    val breakfast_data: LiveData<JSONObject>


    init {

        this.breakfast_data = repo.break_data
    }

    fun breakfastfunction() {
        repo.read_json()
    }




}