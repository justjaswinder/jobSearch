package com.example.mohitmvvmfirebase.models

import com.google.firebase.database.Exclude


/// in kotlin model class is used to store the data

data class User(
    var id: String? = "",
    var name: String? = "",
    var email: String? = "",
    var profile_pic: String? = ""
) {


    // toMap is used to Store Multipale data in List
    // when we add or get data we use tomap

    @Exclude
    fun toMap(): Map<String, Any> {
        val result = HashMap<String, Any>()
        result.put("id", id!!)
        result.put("name", name!!)
        result.put("email", email!!)
        result.put("profile_pic", profile_pic!!)

        return result
    }


}


