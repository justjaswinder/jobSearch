package com.example.mohitmvvmfirebase.ui.activity.signup

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mohitmvvmfirebase.R
import com.example.mohitmvvmfirebase.databinding.ActivitySignupBinding
import com.example.mohitmvvmfirebase.models.User
import com.example.mohitmvvmfirebase.ui.activity.BaseActivity
import com.example.mohitmvvmfirebase.utils.startHomeActivity

class SignupActivity : BaseActivity() {


    private var mFileUri: Uri? = null
    var binding: ActivitySignupBinding? = null
    var user = User()
    var REQUST_CAMERA = 101


    private lateinit var viewModel: SignupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        viewModel = ViewModelProviders.of(this).get(SignupViewModel::class.java)
        binding?.viewmodel = viewModel

        viewModel.muserCreated.observe(this, Observer {
            startHomeActivity()
        })

        viewModel.apiLoader.observe(this, Observer {
            if (it) {
                showProcessDialog(this, "User Is Creating..")
            } else {
                hideProcessDialog()
            }
        })

        viewModel.image_pick_picker.observe(this, Observer {
            if (it) {
                image_picker()
            }
        })


        //binding.setUser(new User("Princess Diana", "USA"));
        //binding?.imageUrl = "https://androidwave.com/wp-content/uploads/2019/01/profile_pic.jpg"

    }


    //     set onclick in xml for pick image
    fun image_picker() {
        // Pick an image from storage
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, REQUST_CAMERA)

    }

    //
    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //Log.d(TAG, "onActivityResult:$requestCode:$resultCode:$data")
        if (requestCode == REQUST_CAMERA) {
            if (resultCode == Activity.RESULT_OK) {
                mFileUri = data?.data
                if (mFileUri != null) {

                    Glide.with(this)
                        .load(mFileUri) // Uri of the picture
                        .apply(RequestOptions.circleCropTransform())
                        .into(binding!!.userProfilePhoto)//binding?.userProfilePhoto

                    //send file Uri to viewModel
                    viewModel.mFile_viewModel=mFileUri

                } else {
                    Log.w(ContentValues.TAG, "File URI is null")
                }
            } else {
                Toast.makeText(this, "Taking picture failed.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
