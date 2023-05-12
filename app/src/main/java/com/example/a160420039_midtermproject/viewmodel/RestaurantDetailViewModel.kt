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

class RestaurantDetailViewModel(application: Application) : AndroidViewModel(application) {
    val restaurantLD = MutableLiveData<Restaurant>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(restaurant_id:String?) {
//        val restaurant1 = Restaurant("1", "Senewen Time", "7/10", "Ini Senewen", "")
//        restaurantLD.value = restaurant1

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://memeworldzz.000webhostapp.com/api/restaurant_list.php?id=$restaurant_id"
        val stringRequest = StringRequest(
            Request.Method.GET, url,{
                val result = Gson().fromJson<Restaurant>(it, Restaurant::class.java)
                restaurantLD.value = result

                Log.d("showVolley", it.toString())
            },
            {
                Log.d("showVolley", it.toString())
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}