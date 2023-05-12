package com.example.a160420039_midtermproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.a160420039_midtermproject.model.Profile

class ProfileListViewModel(application: Application)
    : AndroidViewModel(application){

    val profileLD = MutableLiveData<Profile>()

    fun fetch(username:String?, password:String?){
        val profile1 = Profile("Ardi", "ardi123@gmail.com","qwerty123","0851XXXXX","https://static.wikia.nocookie.net/vsbattles/images/3/35/Tinky_Winky.png/revision/latest?cb=20220216041944")
        val profile2 = Profile("Steven", "steven123@gmail.com", "asdfgh123","0854XXXXX", "https://assets.mycast.io/actor_images/actor-po-teletubbies-734751_large.jpg?1681346112")
        val profile3 = Profile("Alfath", "alfath123@gmail.com", "zxcvbn123","0812XXXXX", "https://w7.pngwing.com/pngs/138/653/png-transparent-green-teletubbies-illustration-pui-fan-lee-teletubbies-dipsy-laa-laa-tinky-winky-versus-celebrities-television-fictional-character-thumbnail.png")

        if(username == "Ardi" && password == "qwerty123"){
            profileLD.value = profile1
        }
        else if(username == "Steven" && password == "asdfgh123"){
            profileLD.value = profile2
        }
        else if(username == "Alfath" && password == "zxcvbn123"){
            profileLD.value = profile3
        }
    }

}