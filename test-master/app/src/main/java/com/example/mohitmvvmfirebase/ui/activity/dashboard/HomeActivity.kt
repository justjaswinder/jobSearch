package net.simplifiedcoding.mohit.data.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.mohitmvvmfirebase.R
import com.example.mohitmvvmfirebase.databinding.ActivityHomeBinding
import com.example.mohitmvvmfirebase.ui.activity.dashboard.HomeViewModel

class HomeActivity : AppCompatActivity() {


    private lateinit var home_viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val binding: ActivityHomeBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_home)

        home_viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        binding.viewmodel = home_viewModel

    }

}
