package com.example.mohitmvvmfirebase.utils

interface FirebaseNetworkCallBack {
    fun onSuccess(response: Any)
    fun onError(excecption: String)
}