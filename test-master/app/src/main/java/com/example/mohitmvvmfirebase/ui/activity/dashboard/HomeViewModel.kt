package com.example.mohitmvvmfirebase.ui.activity.dashboard

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mohitmvvmfirebase.repository.firebaseRepo.FirebaseRepository
import com.example.mohitmvvmfirebase.utils.*

class HomeViewModel : ViewModel() {

    val repository= FirebaseRepository()



    val user by lazy {
        //repository.currentUser()
        repository.currentUser()
    }

   
    val userName by lazy{
        Utility.usernameFromEmail(repository.currentUser()?.email!!)
    }

    fun logout(view: View){
//        repository.logout()
        repository.makeSignOut()
        view.context.startLoginActivity()
    }

    fun addDB(view:View){
        view.context.startAddDBActivity()
    }
    fun storage(view: View){
        view.context.startStorageActivity()
    }

    fun db_list(view: View){
        view.context.startUserListActivity()
    }
}