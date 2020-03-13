package com.example.mohitmvvmfirebase.repository.user_repo


// userRepo only use auth data
//class UserRepository (private val firebase_mAuth: FirebaseAuthController){//,private val firebse_mDB: FirebaseDBController
//
//    //here we access the methods of FirebaseSource/FirebaseController
//    fun login(email: String, password: String) = firebase_mAuth.makeLoginWithEmail(email, password)
//
//    fun register(email: String, password: String) = firebase_mAuth.createUser(email, password)
//
//    fun currentUser() = firebase_mAuth.getCurrentUser()
//
//    fun logout() = firebase_mAuth.makeSignOut()
//
//    //fun createDBuser(id: String?, name: String?, email: String?, profile_pic: String?)= firebse_mDB.onCreateUser(id,name,email,profile_pic)
//}