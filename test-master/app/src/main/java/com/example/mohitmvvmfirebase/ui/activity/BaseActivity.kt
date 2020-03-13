package com.example.mohitmvvmfirebase.ui.activity

import android.app.ProgressDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

open class BaseActivity : AppCompatActivity() {


    var baseprogress: ProgressDialog? = null


    // container is frameLayout that is xml  that is present in dashboard screen

    //<?xml version="1.0" encoding="utf-8"?>
    //<FrameLayout
    //    xmlns:android="http://schemas.android.com/apk/res/android"
    //    android:layout_width="match_parent"
    //    android:layout_height="match_parent"
    //    android:id="@+id/container"/>

    fun attachFragmentDashBoard(fragment: Fragment?) {
//        fragment?.let {
//            supportFragmentManager
//                ?.beginTransaction()
//                ?.add(R.id.container, it)
//                ?.commitAllowingStateLoss()
//        }
    }

    fun showProcessDialog(context: Context?, message: String?) {
        baseprogress = ProgressDialog(context).apply {
            setTitle("Loading..")
            setCancelable(false)
            setCanceledOnTouchOutside(false)
            show()
        }
    }

    fun hideProcessDialog() {
        baseprogress?.dismiss()
    }
}