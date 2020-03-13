package com.example.mohitmvvmfirebase

import android.app.Application
import android.content.Context

class MohitApp : Application() {

    init {
        instance = this
    }

    companion object {
        //for get Application Context
        private var instance: MohitApp? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }
}