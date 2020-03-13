package com.example.mohitmvvmfirebase.ui.activity.storage

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mohitmvvmfirebase.repository.firebaseRepo.FirebaseRepository
import com.example.mohitmvvmfirebase.utils.FirebaseNetworkCallBack

class StrorageViewModel : ViewModel() {


    //email and password for the input
    var name: String? = null
    var email: String? = null
    var password: String? = null


    val apiLoader = MutableLiveData<Boolean>()
    val pick_image = MutableLiveData<Boolean>()
    val repository = FirebaseRepository()
    val muserCreated = MutableLiveData<String>()


    val user by lazy {
        pick_image.value = false
        repository.currentUser()
    }


    fun pickImage() {
        pick_image.value = true
    }

    fun uploadImage(mfileuri: Uri) {
        apiLoader.value = true
        repository.UploadtoFirebaseStorage(mfileuri, object : FirebaseNetworkCallBack {
            override fun onSuccess(response: Any) {
                apiLoader.value = false
            }

            override fun onError(excecption: String) {
                apiLoader.value = false

            }

        })
    }

}