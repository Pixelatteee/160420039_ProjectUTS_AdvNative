package com.example.a160420039_midtermproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.a160420039_midtermproject.R
import com.example.a160420039_midtermproject.util.loadImage
import com.example.a160420039_midtermproject.viewmodel.RestaurantDetailViewModel
import org.w3c.dom.Text


class RestaurantDetailFragment : Fragment() {
    private lateinit var viewModel: RestaurantDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var id = ""
        if(arguments != null){
            val restaurantID = RestaurantDetailFragmentArgs.fromBundle(requireArguments()).restaurantId
            id = restaurantID
        }

        viewModel = ViewModelProvider(this).get(RestaurantDetailViewModel::class.java)
        viewModel.fetch(id)

        ObserveViewModel()
    }

    fun ObserveViewModel(){
        val restaurantName = view?.findViewById<TextView>(R.id.txtRestaurantNameDetail)
        val restaurantRating = view?.findViewById<TextView>(R.id.txtRatingDetail)
        val restaurantDescription = view?.findViewById<TextView>(R.id.txtDescription)
        val photoUrl = view?.findViewById<ImageView>(R.id.imageView)

        viewModel.restaurantLD.observe(viewLifecycleOwner, Observer {
            restaurantName?.text = viewModel.restaurantLD.value?.restaurantName
            restaurantRating?.text = viewModel.restaurantLD.value?.rating
            restaurantDescription?.text = viewModel.restaurantLD.value?.description
            photoUrl?.loadImage(viewModel.restaurantLD.value?.photoUrl)
        })
    }
}