package com.example.mohitmvvmfirebase.updated

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class FirebaseRepository {
    private val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }


    fun userLogin(email: String, password: String): Task<AuthResult> {
        return firebaseAuth.signInWithEmailAndPassword(email, password)
    }


}