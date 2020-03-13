package com.example.mohitmvvmfirebase.updated

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser

class FirebaseViewModel : ViewModel() {

    var email: String? = ""
    var password: String? = ""

    //when need that init this code
    val firebaseRepository by lazy { FirebaseRepository() }

    var validationError: MutableLiveData<String> = MutableLiveData()
    var firebaseUser: MutableLiveData<FirebaseUser> = MutableLiveData() //sucess
    var loader: MutableLiveData<Boolean> = MutableLiveData()
    var gotoSign_up: MutableLiveData<Boolean> = MutableLiveData()

    fun login(email: String, password: String) {
        //show loader
        loader.value = true
        if (isDataValidate(email, password)) {
            firebaseRepository.userLogin(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    //hide loader
                    loader.value = false
                    firebaseUser.postValue(it.result?.user)
                } else {
                    //hide loader
                    loader.value = false
                    validationError.postValue(it.exception!!.localizedMessage)
                }
            }
        }
    }

    fun gotoSignup() {
        gotoSign_up.value = true
    }


    private fun isDataValidate(email: String, password: String): Boolean {
        if (TextUtils.isEmpty(email)) {
            validationError.postValue("Email Empty")
        } else if (TextUtils.isEmpty(password)) {
            validationError.postValue("Password Empty")
        }
        return !TextUtils.isEmpty(email)
                && !TextUtils.isEmpty(password)
    }


}