package com.example.mohit.model

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties


@IgnoreExtraProperties
data class Post(
    val uid: String = "",
    val name: String = "",
    val caption: String = "",
    val message: String = ""
) {


    @Exclude
    fun toMap(): Map<String, Any> {
        val result = HashMap<String, Any>()
        result["uid"] = uid
        result["name"] = name
        result["caption"] = caption
        result["message"] = message

        return result
    }
}