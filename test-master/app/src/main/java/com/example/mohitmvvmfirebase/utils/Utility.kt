package com.example.mohitmvvmfirebase.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

object Utility {


    //mohit
    fun Context.showDialog(activity: Activity?, message: String?) {
        val dialagBuilder = AlertDialog.Builder(activity)
        dialagBuilder.setMessage(message)
            .setCancelable(true)
            .setNegativeButton("ok",
                DialogInterface.OnClickListener { dialag, id ->
                    run { dialag.cancel() }
                })
        //.setPositiveButton("Ok",DialogInterface.OnClickListener({dialogInterface, i -> run {  }  }))
        // create dialog box
        val alert = dialagBuilder.create()
        // set title for alert dialog box
        alert.setTitle("Alert!!")
        // show alert dialog
        alert.show()
    }


    // get name Through Email
    fun usernameFromEmail(email: String): String {
        return if (email.contains("@")) {
            email.split("@".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
        } else {
            email
        }
    }


}