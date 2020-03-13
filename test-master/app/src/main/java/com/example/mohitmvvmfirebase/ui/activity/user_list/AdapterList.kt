package com.example.mohitmvvmfirebase.ui.activity.user_list


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mohitmvvmfirebase.MohitApp
import com.example.mohitmvvmfirebase.R
import com.example.mohitmvvmfirebase.models.User

/**
 * Created by Belal on 6/19/2017.
 */

class AdapterList(val userList: ArrayList<User>, listner: AdapterList_Listner) :
    RecyclerView.Adapter<AdapterList.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_layout_getalluser, parent, false)


        return ViewHolder(v)
    }

    var mListner: AdapterList_Listner = listner
    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(userList[position], mListner)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(user: User, mLister: AdapterList_Listner) {

            val textViewName = itemView.findViewById(R.id.tv_list_title) as TextView
            val textViewEmail = itemView.findViewById(R.id.tv_list_email) as TextView
            val iv_profile = itemView.findViewById(R.id.iv_list_profile) as ImageView
            val iv_delete = itemView.findViewById(R.id.iv_delete) as ImageView

            textViewName.text = user.name
            textViewEmail.text = user.email

            Glide.with(MohitApp.applicationContext())
                .load(user.profile_pic)
                .apply(RequestOptions.circleCropTransform())
                .into(iv_profile)



            iv_delete.setOnClickListener(View.OnClickListener {
                mLister.onDeleteuser(user.id.toString())
                //Toast.makeText(MyApplication.applicationContext(),""+it,Toast.LENGTH_LONG).show()
            })
        }
    }

    interface AdapterList_Listner {
        fun onDeleteuser(userID: String)
    }
}