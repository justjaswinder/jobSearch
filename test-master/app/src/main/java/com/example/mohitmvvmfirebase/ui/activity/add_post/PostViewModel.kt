package com.example.mohitmvvmfirebase.ui.activity.add_post

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mohitmvvmfirebase.repository.firebaseRepo.FirebaseRepository
import com.example.mohitmvvmfirebase.utils.FirebaseNetworkCallBack

class PostViewModel : ViewModel() {


    var name: String? = null
    var caption: String? = null
    var message: String? = null

    val mUserDbPOST = MutableLiveData<String>()
    val mGeneralError = MutableLiveData<String>()
    val repository = FirebaseRepository()


    val user by lazy {
        repository.currentUser()
    }


    //function to perform login
    fun addDBuser() {

        //validating email and password
        if (name.isNullOrEmpty() || caption.isNullOrEmpty() || message.isNullOrEmpty()) {
            mGeneralError.postValue("Enter All fields ")
            return
        }

        repository.onCreatePOST(
            user!!.uid,
            name,
            caption,
            message,
            object : FirebaseNetworkCallBack {

                override fun onSuccess(response: Any) {
                    //here callback user returns FirebaseUser
                    mUserDbPOST.value = response as String
                }

                override fun onError(excecption: String) {
                    mGeneralError.value = excecption
                }

            })
    }
}