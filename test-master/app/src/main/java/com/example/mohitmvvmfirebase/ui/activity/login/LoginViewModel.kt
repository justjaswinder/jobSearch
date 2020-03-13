package com.example.mohitmvvmfirebase.ui.activity.login

import android.content.Intent
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mohitmvvmfirebase.models.User
import com.example.mohitmvvmfirebase.repository.firebaseRepo.FirebaseRepository
import com.example.mohitmvvmfirebase.ui.activity.signup.SignupActivity
import com.example.mohitmvvmfirebase.utils.FirebaseNetworkCallBack
import com.example.mohitmvvmfirebase.utils.Utility
import com.google.firebase.auth.FirebaseUser

class LoginViewModel : ViewModel() {

    //email and password for the input
    var email: String? = null
    var password: String? = null

    var muser = User()

    val mFirebaseUser = MutableLiveData<FirebaseUser>()
    val mGeneralError = MutableLiveData<String>()
    val repository = FirebaseRepository()


    val user by lazy {
        repository.currentUser()
    }

    //function to perform login
    fun login() {

        //validating email and password
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            mGeneralError.postValue("Invalid email or password")
            return
        }

        repository.fireBaseLogin(email!!, password!!, object : FirebaseNetworkCallBack {

            override fun onSuccess(response: Any) {
                //here callback user returns FirebaseUser
                mFirebaseUser.value = response as FirebaseUser

                //add data to model here
                muser.email = response.email
                muser.id = response.uid
                muser.profile_pic = response.email
                muser.name = Utility.usernameFromEmail(response.email.toString())
            }

            override fun onError(excecption: String) {
                mGeneralError.value = excecption
            }

        })
    }

    fun goToSignup(view: View) {


        Intent(view.context, SignupActivity::class.java).also {
            view.context.startActivity(it)
        }

    }
}