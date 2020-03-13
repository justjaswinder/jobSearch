package com.example.mohitmvvmfirebase.ui.activity.storage

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.mohitmvvmfirebase.R
import com.example.mohitmvvmfirebase.databinding.ActivityStorageBinding
import com.example.mohitmvvmfirebase.ui.activity.BaseActivity
import com.example.mohitmvvmfirebase.utils.startHomeActivity
import kotlinx.android.synthetic.main.activity_storage.*

class StorageActivity : BaseActivity() {


    private val REQUST_CAMERA = 101
    private var mFileUri: Uri? = null
    var binding: ActivityStorageBinding? = null

    private lateinit var viewModel: StrorageViewModel

//    private val mFirebaseStorageController by lazy { FirebaseStorageController(this@StorageActivity, object : FirebaseStorageResultCallback {
//        override fun onGetProfileImage(stringUri: String?) {
//
//        }
//
//        override fun onStorageSuccess(message: String) {
//                processBar.isGone
//                image_pick_btn.isVisible
//                Toast.makeText(this@StorageActivity,message,Toast.LENGTH_LONG).show()
//
//                val intent = Intent(this@StorageActivity, DashboardActivity::class.java)
//                startActivity(intent)
//            }
//            override fun onStorageFailure(message: String) {
//                processBar.isGone
//                image_pick_btn.isVisible
//                Toast.makeText(this@StorageActivity,message,Toast.LENGTH_LONG).show()
//            }
//
//        })
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_storage)

        viewModel = ViewModelProviders.of(this).get(StrorageViewModel::class.java)
        binding?.viewmodel = viewModel

//
        set_observer()

    }

    private fun set_observer() {
        viewModel.muserCreated.observe(this, Observer {
            startHomeActivity()
        })

        // api loader is on when image is upload
        viewModel.apiLoader.observe(this, Observer {
            if (it) {
                showProcessDialog(this, "Image is Uploading..")
            } else {
                hideProcessDialog()
                Toast.makeText(this, "Your Image is Uploaded..", Toast.LENGTH_SHORT).show()
                startHomeActivity()

            }
        })
        viewModel.pick_image.observe(this, Observer {
            if (it) {
                //showProcessDialog(this,"User Is Creating..")
                lauchCamera()
            }
        })
    }

    //
//    override fun onClick(view: View?) {
//     when(view?.id){
//         R.id.image_pick_btn->  {
//         lauchCamera()
//         }
//     }
//    }
//
    private fun lauchCamera() {
        // Pick an image from storage
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, REQUST_CAMERA)

    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //Log.d(TAG, "onActivityResult:$requestCode:$resultCode:$data")
        if (requestCode == REQUST_CAMERA) {
            if (resultCode == Activity.RESULT_OK) {
                mFileUri = data?.data
                if (mFileUri != null) {
                    //uploadFromUri(mFileUri!!)

                    // upload image here
                    viewModel.uploadImage(mFileUri!!)

                    Glide.with(this@StorageActivity)
                        .load(mFileUri) // Uri of the picture
                        .into(iv_display_image)

//                    Toast.makeText(MyApplication.applicationContext(),""+mFileUri,Toast.LENGTH_LONG).show()

                }
//                else {
////                    Log.w(TAG, "File URI is null")
//                }
            } else {
                Toast.makeText(this, "Taking picture failed.", Toast.LENGTH_SHORT).show()
            }
        }
    }


}




