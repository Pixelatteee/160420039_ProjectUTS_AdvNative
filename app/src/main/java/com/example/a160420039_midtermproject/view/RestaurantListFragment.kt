package com.example.a160420039_midtermproject.view

import android.os.Bundle
import android.os.RecoverySystem
import android.support.v4.app.INotificationSideChannel.Default
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.a160420039_midtermproject.Global
import com.example.a160420039_midtermproject.R
import com.example.a160420039_midtermproject.viewmodel.RestaurantListViewModel

class RestaurantListFragment : Fragment() {

    private lateinit var viewModel:RestaurantListViewModel
    private val restaurantListAdapter = RestaurantListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recView = view.findViewById<RecyclerView>(R.id.recView)
        val refreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.refreshLayout)
        val progressLoad = view.findViewById<ProgressBar>(R.id.progressLoad)
        val txtError = view.findViewById<TextView>(R.id.txtError)

        if(Global.username.isEmpty()){
            val action = RestaurantListFragmentDirections.actionLoginFragment()
            Navigation.findNavController(view).navigate(action)
        }

        viewModel = ViewModelProvider(this).get(RestaurantListViewModel::class.java)
        viewModel.refresh()

        recView?.layoutManager = LinearLayoutManager(context)
        recView?.adapter = restaurantListAdapter

        refreshLayout?.setOnRefreshListener {
            recView?.visibility = View.GONE
            txtError?.visibility = View.GONE
            progressLoad?.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }

        observeViewModel()
    }

    fun observeViewModel(){
        val recView = view?.findViewById<RecyclerView>(R.id.recView)
        val progressLoad = view?.findViewById<ProgressBar>(R.id.progressLoad)
        val txtError = view?.findViewById<TextView>(R.id.txtError)

        viewModel.restaurantLD.observe(viewLifecycleOwner, Observer {
            restaurantListAdapter.updateRestaurantList(it)
        })

        viewModel.restaurantLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                txtError?.visibility = View.VISIBLE
            } else {
                txtError?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                recView?.visibility = View.GONE
                progressLoad?.visibility = View.VISIBLE
            } else {
                recView?.visibility = View.VISIBLE
                progressLoad?.visibility = View.GONE
            }
        })

    }

}