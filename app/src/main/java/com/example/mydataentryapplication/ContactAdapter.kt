package com.example.mydataentryapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ContactAdapter(
    val listofContact:List<ContactItem>
): RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    class ViewHolder(
        view: View
    ):RecyclerView.ViewHolder(view){
        val image:ImageView=view.findViewById(R.id.profile_image)
        val name:TextView=view.findViewById(R.id.profile_name)
        val phone_no:TextView=view.findViewById(R.id.number)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.layout_contact,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listofContact.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.image.setImageURI(listofContact[position].image)
        holder.name.text=listofContact[position].name
        holder.phone_no.text=listofContact[position].phone_no
    }

}