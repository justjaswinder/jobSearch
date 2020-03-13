package com.example.mohitmvvmfirebase.updated

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mohitmvvmfirebase.R
import com.example.mohitmvvmfirebase.databinding.FirebaseLoginBinding
import com.example.mohitmvvmfirebase.ui.activity.BaseActivity
import com.example.mohitmvvmfirebase.utils.startHomeActivity
import com.example.mohitmvvmfirebase.utils.startSignupActivity
import com.example.mohitmvvmfirebase.utils.toast

class FireBaseLoginActivity : BaseActivity() {

    private lateinit var viewModel: FirebaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: FirebaseLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.firebase_login)

        viewModel = ViewModelProviders.of(this).get(FirebaseViewModel::class.java)
        binding.viewmodel = viewModel


        observeData()

    }

    private fun observeData() {

        //observe data
        viewModel.firebaseUser.observe(this, Observer {
            it?.let {
                toast(it.email)
                startHomeActivity()
            }
        })
        viewModel.validationError.observe(this, Observer {
            it?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                toast(it)
            }
        })
        viewModel.loader.observe(this, Observer {
            if (it) {
                showProcessDialog(this, "Loading..")
            } else {
                hideProcessDialog()
            }
        })
        viewModel.gotoSign_up.observe(this, Observer {
            if (it) {
                startSignupActivity()
            }
        })
    }


}

