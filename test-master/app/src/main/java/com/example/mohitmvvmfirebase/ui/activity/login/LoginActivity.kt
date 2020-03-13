package com.example.mohitmvvmfirebase.ui.activity.login

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mohitmvvmfirebase.R
import com.example.mohitmvvmfirebase.databinding.ActivityLoginBinding
import com.example.mohitmvvmfirebase.models.User
import com.example.mohitmvvmfirebase.ui.activity.BaseActivity
import com.example.mohitmvvmfirebase.utils.startHomeActivity

class LoginActivity : BaseActivity() {

    private lateinit var viewModel: LoginViewModel

    var user = User()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //databinding
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)


        //here viewmodel is data_LAYOUT_variable
        // and viewModel is AuthViewModel
        //pass AuthViewMOdel to Layout To bind The Data when activity is created
        binding.viewmodel = viewModel


        //observe live data
        observeData()

//        viewModel.liveData_login.observe(this, Observer {
//            //send data to model
//            user.email=it.email
//            user.name= Utility.usernameFromEmail(it.email.toString())
//            user.profile_pic=it.displayName
//
//            startHomeActivity()
//
//        })

    }

    private fun observeData() {
        // observe live datas
        viewModel.mFirebaseUser.observe(this, Observer {

            startHomeActivity()
        })

        viewModel.mGeneralError.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }


    // when activity is start
    override fun onStart() {
        super.onStart()

        //viewModel.user == means user is available or not
        viewModel.user?.let {
            startHomeActivity()
        }
    }


}

