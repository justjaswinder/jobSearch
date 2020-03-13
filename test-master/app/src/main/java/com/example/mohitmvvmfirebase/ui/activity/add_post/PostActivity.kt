package com.example.mohit.ui.activity.post.add_post

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mohitmvvmfirebase.R
import com.example.mohitmvvmfirebase.databinding.ActivityPostBinding
import com.example.mohitmvvmfirebase.ui.activity.BaseActivity
import com.example.mohitmvvmfirebase.ui.activity.add_post.PostViewModel
import com.example.mohitmvvmfirebase.utils.startHomeActivity

class PostActivity : BaseActivity() {

    // private val FirebaseDataBaseController by lazy { FirebaseDBController(this@PostActivity, this) }
    private lateinit var viewModel: PostViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //databinding
        val binding: ActivityPostBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_post)

        viewModel = ViewModelProviders.of(this).get(PostViewModel::class.java)


        //here viewmodel is data_LAYOUT_variable
        // and viewModel is AuthViewModel
        //pass AuthViewMOdel to Layout To bind The Data when activity is created
        binding.viewmodel = viewModel


        //observe live data
        observeData()


    }

    private fun observeData() {

        // observe live datas
        viewModel.mUserDbPOST.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            startHomeActivity()
        })

        viewModel.mGeneralError.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

    }

//
//    override fun onClick(p0: View?) {
//        when (p0?.id) {
//            R.id.add_post -> {
//
//                FirebaseDataBaseController.onCreatePOST("dsf43246e45te5drf43gerf4",edtName?.text.toString(),caption?.text.toString(),message?.text.toString())
//
//                    val intent = Intent (this@PostActivity, DashboardActivity::class.java)
//                    startActivity(intent)
//
//            }
//        }
//
//    }
//
//
//    override fun onDBSuccess(message: String) {
//        Toast.makeText(this@PostActivity,message,Toast.LENGTH_SHORT).show()
//    }
//
//    override fun onDBFailure(message: String) {
//        Toast.makeText(this@PostActivity,message,Toast.LENGTH_SHORT).show()
//    }


}


