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
import com.example.a160420039_midtermproject.Global
import com.example.a160420039_midtermproject.R
import com.example.a160420039_midtermproject.util.loadImage
import com.example.a160420039_midtermproject.viewmodel.ProfileListViewModel
import com.google.android.material.textfield.TextInputEditText

class ProfileFragment : Fragment() {
    private lateinit var viewModel: ProfileListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ProfileListViewModel::class.java)
        viewModel.fetch(Global.username, Global.password)

        ObserveViewModel()
    }

    fun ObserveViewModel(){
        val username = view?.findViewById<TextView>(R.id.txtUsername)
        val email = view?.findViewById<TextView>(R.id.txtEmail)
        val phone = view?.findViewById<TextView>(R.id.txtPhone)
        val photoUrl = view?.findViewById<ImageView>(R.id.imageView3)

        viewModel.profileLD.observe(viewLifecycleOwner, Observer {
            username?.text = viewModel.profileLD.value?.username
            email?.text = viewModel.profileLD.value?.email
            phone?.text = viewModel.profileLD.value?.phone
            photoUrl?.loadImage(viewModel.profileLD.value?.photoURL)
        })
    }
}