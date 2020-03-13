package com.example.mohitmvvmfirebase.repository.firebaseRepo

import Posts
import android.net.Uri
import android.util.Log
import com.example.mohit.model.Add_post
import com.example.mohitmvvmfirebase.models.User
import com.example.mohitmvvmfirebase.utils.Constants
import com.example.mohitmvvmfirebase.utils.Constants.Storage_Base_URL
import com.example.mohitmvvmfirebase.utils.FirebaseNetworkCallBack
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference



class FirebaseRepository {


    private val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val firebaseDB: DatabaseReference by lazy {
        FirebaseDatabase.getInstance().getReferenceFromUrl(Constants.FIREBASE_DB_URL)
    }
    private val firebaseStroage: StorageReference by lazy {
        FirebaseStorage.getInstance().getReferenceFromUrl(Storage_Base_URL)
    }


    //login
    fun fireBaseLogin(email: String, password: String, callBack: FirebaseNetworkCallBack) {

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful)
                callBack.onSuccess(it.result?.user!!)
            else {
                callBack.onError(it.exception!!.localizedMessage)
            }
        }
    }

    //create New User
    fun firebaseSignup(email: String, password: String, callBack: FirebaseNetworkCallBack) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                callBack.onSuccess(task.result?.user!!)
            } else {
                callBack.onError(task.exception!!.localizedMessage)

            }
        }

    }

    //get instance of current user
    fun currentUser() = firebaseAuth.currentUser

    // Login With Email Password
    fun makeSignOut() {
        firebaseAuth.signOut()
    }


///////////////////////////////////////////////////////// DATABASE ////////////////////////////////////////////////////


    //when User Create The Database is Created
    fun onCreateUser(id: String?, name: String?, email: String?, profile_pic: String?, callBack: FirebaseNetworkCallBack) {

        // this User model
        val user = User(id!!, name!!, email!!, profile_pic!!)
        // Create Database on firebase Database
        firebaseDB.child("users").child(id).setValue(user).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                //listnerDB?.onDBSuccess("Database Added Successfully")
                callBack.onSuccess("User Is Created")

            } else {
                //listnerDB?.onDBFailure("Data Not Added")
                callBack.onError(task.exception!!.localizedMessage)

            }
        }
    }


    // When User Add the Data
    fun onCreatePOST(
        uid: String?,
        name: String?,
        caption: String?,
        body: String?,
        callBack: FirebaseNetworkCallBack
    ) {
        // this User model
        val add_post = Add_post(uid, name, caption, body)

        // Create post Database on firebase Database
     //   firebaseDB.ref.
     // firebaseDB.child("users").child(uid.toString()).child("posts").setValue(add_post)
        firebaseDB.child("posts").child(name.toString()).setValue(add_post)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callBack.onSuccess("Your Post Is Saved")
                    //listnerDB?.onDBSuccess("Your Post Is Saved")
                } else {
                    callBack.onError("Post Not Saved")
                    //listnerDB?.onDBFailure("Post Not Saved")
                }
            }
    }


    //get all users data in list To show the
    fun getAllUsers(callBack: FirebaseNetworkCallBack) {

        //to fetch all the users of firebase Auth app
        val eventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

//                dataSnapshot.children.mapNotNullTo(menu)
//                { it.getValue<Posts>(Posts::class.java)


//                    for (ds in dataSnapshot.child("posts").children) {
//                   // ds.key("")
//                    ds.getValue().toString()
//      var pos = ds.getValue(Posts::class.java)
//
//                //    pos?.name.toString()
//
//                        val map = ds.getValue() as Map<*, *>
//                     //   for (mapp in map.entries) {
//
////.child("posts").child(mapp.key as String)
//                            val user_name =   ds.child("name").getValue(String::class.java)// ds.child("name").getValue(String::class.java)
//                        //    val user_id = ds.child(mapp.key as String).child("id").getValue(String::class.java)
//                        val user_id = ds.child("id").getValue(String::class.java)
//                            val user_email = ds.child("message").getValue(String::class.java)
//                            val user_profile = ds.child("profile_pic").getValue(String::class.java)
//
//                            val user = User(user_id, user_name, user_email, user_profile)
//
//                            val arrayList = ArrayList<User>()
//
//                            //arrayList.add(user)
//
//                            callBack.onSuccess(user)
//                       // }
//
//                }

                for (ds in dataSnapshot.child("users").children) {
                    // ds.key("")
                    ds.getValue().toString()
                    var pos = ds.getValue(Posts::class.java)

                    //    pos?.name.toString()

                    val map = ds.getValue() as Map<*, *>
                    //   for (mapp in map.entries) {

//.child("posts").child(mapp.key as String)
                    val user_name =   ds.child("name").getValue(String::class.java)// ds.child("name").getValue(String::class.java)
                    //    val user_id = ds.child(mapp.key as String).child("id").getValue(String::class.java)
                    val user_id = ds.child("id").getValue(String::class.java)
                    val user_email = ds.child("message").getValue(String::class.java)
                    val user_profile = ds.child("profile_pic").getValue(String::class.java)

                    val user = User(user_id, user_name, user_email, user_profile)

                    val arrayList = ArrayList<User>()

                    //arrayList.add(user)

                    callBack.onSuccess(user)
                    // }

                }


            }

            override fun onCancelled(databaseError: DatabaseError) {
                callBack.onError(databaseError.toString())

            }
        }
        firebaseDB.addListenerForSingleValueEvent(eventListener)
    }





    //Delet User
    fun deleteUser(user_id:String,callBack: FirebaseNetworkCallBack){

        //mDB_deleteUser?.child(user_id.toString())

        firebaseDB.child(user_id).removeValue().addOnSuccessListener {
            // Write was successful!
            // ...
            callBack.onSuccess("User is Deleted")


        }.addOnFailureListener {
                // Write failed
                // ...
                callBack.onError(""+it)
    //
            }

    }

///////////////////////////////////////////////////////// STORAGE ////////////////////////////////////////////////////


    fun UploadtoFirebaseStorage(fileUri: Uri?, callBack: FirebaseNetworkCallBack) {
        // Save the File URI
        if (fileUri != null) {
            val photoRef =
                firebaseStroage.child("/Storage").child(fileUri.lastPathSegment.toString())
            photoRef.putFile(fileUri).addOnSuccessListener { taskSnapshot ->
                // Upload succeeded

            //    Log.e("Image URL",

              val task =   taskSnapshot.getStorage().getDownloadUrl();
                task.addOnSuccessListener { uri ->
                    val repository = FirebaseRepository()
//https://firebasestorage.googleapis.com/v0/b/newfirebase-2cbe7.appspot.com/o/Storage%2Fimage%3A18?alt=media&token=2e925d39-2e5c-4d31-909b-718827cd1418

                    Log.e("IMAGE", uri.toString())
                    firebaseDB.child("users").child(repository.currentUser()!!.uid).child("profile_pic").setValue(uri.toString())

                    callBack.onSuccess("Image is Uploaded..")
                }


            //    var imgURL = photoRef.downloadUrl.toString()


                //  firebaseDB.child("users").child(repo).setValue(user).a






                // Getting image name from EditText and store into string variable.
           //     val TempImageName = ImageName.getText().toString().trim()

                // Hiding the progressDialog after done uploading.
              //  progressDialog.dismiss()

                // Showing toast message after done uploading.
//                Toast.makeText(
//                    getApplicationContext(),
//                    "Image Uploaded Successfully ",
//                    Toast.LENGTH_LONG
//                ).show()

//                val imageUploadInfo =
//                    ImageUploadInfo("name", taskSnapshot.getDownloadUrl().toString())
//
//                // Getting image upload ID.
//                val ImageUploadId = databaseReference.push().getKey()
//
//                // Adding image upload id s child element into databaseReference.
//                databaseReference.child(ImageUploadId).setValue(imageUploadInfo)


            }.addOnFailureListener { exception ->
                // Upload failed
                callBack.onError(exception.localizedMessage)
            }
        }

    }

}