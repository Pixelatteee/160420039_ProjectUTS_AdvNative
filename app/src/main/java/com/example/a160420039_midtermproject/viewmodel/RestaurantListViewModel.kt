package com.example.a160420039_midtermproject.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160420039_midtermproject.model.Restaurant
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RestaurantListViewModel(application: Application)
    : AndroidViewModel(application)
{
    val restaurantLD =MutableLiveData<ArrayList<Restaurant>>()
    val restaurantLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue:RequestQueue? = null

    fun refresh(){
//        val restaurant1 = Restaurant("1", "Senewen Time", "7/10", "Ini Senewen", "")
//        val restaurant2 = Restaurant("2", "Kak Ayam Geprek Rose", "6.5/10", "Ini KAG", "")
//        val restaurant3 = Restaurant("3", "Sate Cak Jun", "10/10", "Ini Sate Cak Jun", "")
//
//        val restaurantList:ArrayList<Restaurant> = arrayListOf(restaurant1, restaurant2, restaurant3)

//        restaurantLD.value = restaurantList
        restaurantLoadErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://memeworldzz.000webhostapp.com/api/restaurant_list.php"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Restaurant>>() { }.type
                val result = Gson().fromJson<ArrayList<Restaurant>>(it, sType)
                restaurantLD.value = result
                loadingLD.value = false

                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
                restaurantLoadErrorLD.value = true
                loadingLD.value = true
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}