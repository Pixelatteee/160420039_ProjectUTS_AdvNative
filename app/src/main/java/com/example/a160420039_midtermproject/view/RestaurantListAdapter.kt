package com.example.a160420039_midtermproject.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.a160420039_midtermproject.R
import com.example.a160420039_midtermproject.model.Restaurant
import com.example.a160420039_midtermproject.util.loadImage
import org.w3c.dom.Text

class RestaurantListAdapter(val restaurantList:ArrayList<Restaurant>)
    :RecyclerView.Adapter<RestaurantListAdapter.RestaurantViewHolder>()
{
    class RestaurantViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =inflater.inflate(R.layout.restaurant_list_item, parent, false)

        return RestaurantViewHolder(view)
    }

    override fun getItemCount(): Int {
        return restaurantList.size
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurantName = holder.view.findViewById<TextView>(R.id.txtRestaurantName)
        val rating = holder.view.findViewById<TextView>(R.id.txtRating)
        val btnRestaurantDetail = holder.view.findViewById<Button>(R.id.btnRestaurantDetail)
        val imageView = holder.view.findViewById<ImageView>(R.id.imageView2)
        val progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBar)

        imageView.loadImage(restaurantList[position].photoUrl, progressBar)
        val id:String? = restaurantList[position].id
        restaurantName.text = restaurantList[position].restaurantName
        rating.text = restaurantList[position].rating
        btnRestaurantDetail.setOnClickListener{
            val restaurantID = id.toString()
            val action = RestaurantListFragmentDirections.actionRestaurantDetail(restaurantID)
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateRestaurantList(newRestaurantList: ArrayList<Restaurant>){
        restaurantList.clear()
        restaurantList.addAll(newRestaurantList)
        notifyDataSetChanged()
    }
}