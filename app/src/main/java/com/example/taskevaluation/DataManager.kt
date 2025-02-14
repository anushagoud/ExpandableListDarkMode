package com.example.taskevaluation

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.google.gson.Gson

object DataManager {

    var data = emptyArray<Cities>()
    var isDataLoaded = mutableStateOf(false)

    fun loadFileFromAssets(context: Context) {
        val inputStream = context.assets.open("cities.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        data = gson.fromJson(json, Array<Cities>::class.java)
        isDataLoaded.value = true
    }
}