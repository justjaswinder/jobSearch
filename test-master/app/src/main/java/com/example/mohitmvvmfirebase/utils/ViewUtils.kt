package com.example.mohitmvvmfirebase.utils

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.mohit.ui.activity.post.add_post.PostActivity
import com.example.mohitmvvmfirebase.ui.activity.login.LoginActivity
import com.example.mohitmvvmfirebase.ui.activity.signup.SignupActivity
import com.example.mohitmvvmfirebase.ui.activity.storage.StorageActivity
import com.example.mohitmvvmfirebase.ui.activity.user_list.UserListActivity
import net.simplifiedcoding.mohit.data.ui.home.HomeActivity


fun Context.toast(message: String?) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()


fun Context.startHomeActivity() =
    Intent(this, HomeActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }

fun Context.startLoginActivity() =
    Intent(this, LoginActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }

fun Context.startSignupActivity() =
    Intent(this, SignupActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }

fun Context.startAddDBActivity() =
    Intent(this, PostActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivities(arrayOf(it))
    }

fun Context.startStorageActivity() =
    Intent(this, StorageActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivities(arrayOf(it))
    }

fun Context.startUserListActivity() =
    Intent(this, UserListActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivities(arrayOf(it))
    }


