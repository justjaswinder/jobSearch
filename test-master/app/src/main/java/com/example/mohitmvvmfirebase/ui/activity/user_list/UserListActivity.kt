package com.example.mohitmvvmfirebase.ui.activity.user_list

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mohitmvvmfirebase.R
import com.example.mohitmvvmfirebase.databinding.ActivityUserListBinding
import com.example.mohitmvvmfirebase.models.User
import com.example.mohitmvvmfirebase.repository.firebaseRepo.FirebaseRepository
import com.example.mohitmvvmfirebase.ui.activity.BaseActivity
import com.example.mohitmvvmfirebase.utils.FirebaseNetworkCallBack
import com.example.mohitmvvmfirebase.utils.startHomeActivity
import com.example.mohitmvvmfirebase.utils.toast
import kotlinx.android.synthetic.main.activity_user_list.*


class UserListActivity : BaseActivity(), AdapterList.AdapterList_Listner {

    override fun onDeleteuser(userID: String) {

        repository.deleteUser(userID, object : FirebaseNetworkCallBack{
            override fun onSuccess(response: Any) {
                toast("User is Deleted = "+userID)
                startHomeActivity()
            }
            override fun onError(excecption: String) {
                toast(excecption)
            }
        })
    }

    val repository = FirebaseRepository()
    var arrayList = ArrayList<User>()

    private lateinit var viewModel: UserListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding :ActivityUserListBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_list)

        viewModel = ViewModelProviders.of(this).get(UserListViewModel::class.java)

        binding.viewmodel = viewModel


        //here We get user all User Name
        //here on get List set RecyclerView
        //mFirebaseDataBaseController.getAllUsers()
        repository.getAllUsers(object : FirebaseNetworkCallBack{
            override fun onSuccess(response: Any) {

                arrayList.add(response as User)

                //getting recyclerview from xml
                list_recycler_view.apply {
                    // set a LinearLayoutManager to handle Android
                    // RecyclerView behavior
                    layoutManager = LinearLayoutManager(this@UserListActivity)
                    // set the custom adapter to the RecyclerView
                    layoutManager = LinearLayoutManager(this@UserListActivity)
                    adapter = AdapterList(arrayList,this@UserListActivity)
                }

            }

            override fun onError(excecption: String) {
                toast(excecption)
            }
        })



    }
}







