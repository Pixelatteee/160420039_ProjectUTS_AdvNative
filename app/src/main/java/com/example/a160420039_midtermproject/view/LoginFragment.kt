package com.example.a160420039_midtermproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.a160420039_midtermproject.Global
import com.example.a160420039_midtermproject.R
import com.example.a160420039_midtermproject.model.Profile
import com.example.a160420039_midtermproject.viewmodel.ProfileListViewModel
import com.google.android.material.textfield.TextInputEditText


class LoginFragment : Fragment() {
    private lateinit var viewModel:ProfileListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnLogin = view.findViewById<Button>(R.id.btnLogin)
        val txtUsername = view.findViewById<TextInputEditText>(R.id.txtUsernameLogin)
        val txtPassword = view.findViewById<TextInputEditText>(R.id.txtPasswordLogin)

        btnLogin?.setOnClickListener{
            var username = txtUsername?.text.toString()
            var password = txtPassword?.text.toString()
            viewModel = ViewModelProvider(this).get(ProfileListViewModel::class.java)
            viewModel.fetch(username, password)

            observeViewModel()

            if(viewModel.profileLD.value?.username == username && viewModel.profileLD.value?.password == password){
                Global.username = username
                Global.password = password
                val action = LoginFragmentDirections.actionRestaurantList()
                Navigation.findNavController(it).navigate(action)
            }
            else
            {
                Toast.makeText(activity, "Username or Password wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun observeViewModel(){
        var username = ""
        var password = ""

        viewModel.profileLD.observe(viewLifecycleOwner, Observer {
            username = viewModel.profileLD.value?.username.toString()
            password = viewModel.profileLD.value?.password.toString()
        })
    }
}