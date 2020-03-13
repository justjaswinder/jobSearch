package com.example.mohitmvvmfirebase.ui.activity.signup

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mohitmvvmfirebase.repository.firebaseRepo.FirebaseRepository
import com.example.mohitmvvmfirebase.ui.activity.login.LoginActivity
import com.example.mohitmvvmfirebase.utils.FirebaseNetworkCallBack
import com.example.mohitmvvmfirebase.utils.Utility
import com.google.firebase.auth.FirebaseUser

class SignupViewModel : ViewModel() {

    //email and password for the input
    var name: String? = null
    var email: String? = null
    var password: String? = null
    var mFile_viewModel: Uri? = null

    val apiLoader = MutableLiveData<Boolean>()
    val mFirebaseUser = MutableLiveData<FirebaseUser>()
    val mGeneralError = MutableLiveData<String>()
    val repository = FirebaseRepository()
    val muserCreated = MutableLiveData<String>()
    var image_pick_picker = MutableLiveData<Boolean>()


    val user by lazy {
        repository.currentUser()
    }


    fun goToLogin(view: View) {
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    // perform to signup
    fun signup() {
        apiLoader.value = true

        //validating email and password
        if (name.isNullOrEmpty() || email.isNullOrEmpty() || password.isNullOrEmpty()) {
            mGeneralError.postValue("Invalid email or password")
            return
        }

        repository.firebaseSignup(email!!, password!!, object : FirebaseNetworkCallBack {

            override fun onSuccess(response: Any) {
                //here callback user returns FirebaseUser
                mFirebaseUser.value = response as FirebaseUser

                val name = Utility.usernameFromEmail(response.email.toString())

                createDBuser(response.uid, name, response.email!!, "profile when user create")
            }

            override fun onError(excecption: String) {
                mGeneralError.value = excecption
            }
        })
    }

    private fun createDBuser(uid: String, name: String, email: String, profile_pic: String) {

        repository.onCreateUser(uid, name, email, profile_pic, object : FirebaseNetworkCallBack {
            override fun onSuccess(response: Any) {

                //here callback user returns FirebaseUser
                muserCreated.value = response as String
                //after this observe start new activity


                repository.UploadtoFirebaseStorage(mFile_viewModel,object: FirebaseNetworkCallBack{
                    override fun onSuccess(response: Any) {
                        apiLoader.value = false
                    }

                    override fun onError(excecption: String) {
                        apiLoader.value = false
                        mGeneralError.value = excecption
                    }

                })
            }

            override fun onError(excecption: String) {
                mGeneralError.value = excecption
                apiLoader.value = false

            }
        })
    }

    fun image_picker() {
        image_pick_picker.value = true
    }
}