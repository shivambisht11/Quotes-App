package com.example.quotesapp.data.repo

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.example.quotesapp.Pages
import com.example.quotesapp.data.models.Quote
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object DataManager {

    var data = emptyArray<Quote>()
    var isDataLoaded = mutableStateOf(false)

    var currentPage = mutableStateOf(Pages.LISTING)

    var currentQuote: Quote? = null

    suspend fun loadAssestFormFile(context: Context) {
        try {
            val inputStream = context.assets.open("quotes.json")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val json = String(buffer, Charsets.UTF_8)
            val gson = Gson()
            data = gson.fromJson(json, Array<Quote>::class.java)
            withContext(Dispatchers.Main) {
                isDataLoaded.value = true
            }
        } catch (e: Exception) {
            e.printStackTrace()
            withContext(Dispatchers.Main) {
                isDataLoaded.value = false
            }
        }
    }

    fun switchPages(quote: Quote?){
        if (currentPage.value == Pages.LISTING){
            currentQuote = quote
            currentPage.value = Pages.DETAIL
        }else{
            currentPage.value = Pages.LISTING
        }
    }

}
