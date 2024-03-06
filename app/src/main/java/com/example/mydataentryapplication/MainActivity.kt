package com.example.mydataentryapplication

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.ViewModelFactoryDsl
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var fab : FloatingActionButton
    private lateinit var rv :RecyclerView
    private lateinit var imgbtn:Button
    private lateinit var addconbtn:Button
    private lateinit var imgview:ImageView
    private lateinit var nameedt:EditText
    private lateinit var phoneedt:EditText
    private lateinit var dialog: Dialog

//    val listofContact= mutableListOf<ContactItem>()
    private lateinit var contactaptr:ContactAdapter
    private lateinit var repo: Repo
    private lateinit var contactViewModel: ContactViewModel
    private lateinit var contactViewModelFactory:ContactViewModelFactory



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab =findViewById(R.id.fabbtn)
        rv =findViewById(R.id.rv)

        rv.layoutManager=LinearLayoutManager(this)

        repo=Repo()
        contactViewModelFactory = ContactViewModelFactory(repo)
        contactViewModel=ViewModelProvider(this,contactViewModelFactory).get(ContactViewModel::class.java)

        contactViewModel.liveDataContacts.observe(this){

            contactaptr= ContactAdapter(it)
            rv.adapter=contactaptr
        }







        fab.setOnClickListener(){
            showDialog()
        }

    }
    private fun showDialog(){
        dialog=Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_dialog)

         imgbtn=dialog.findViewById(R.id.Imgbtn)
         addconbtn=dialog.findViewById(R.id.contactbtn)
        imgview=dialog.findViewById(R.id.imageview)
        nameedt=dialog.findViewById(R.id.textnameedt)
        phoneedt=dialog.findViewById(R.id.textphoneedt)

        imgbtn.setOnClickListener{
            val galleryintent=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryintent,1011)
        }

        addconbtn.setOnClickListener(){
            Toast.makeText(this, "Choose Image first!", Toast.LENGTH_SHORT).show()
        }

        dialog.show()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==1011 && resultCode== RESULT_OK){
            imgview.visibility= View.VISIBLE
            imgview.setImageURI(data?.data)

            addconbtn.setOnClickListener(){
               var etrname:String=nameedt.text.toString()
                var etrphone:String=phoneedt.text.toString()
                var previewimg=data?.data

                val contact=ContactItem(
                    image = previewimg!!,
                    name = etrname,
                    phone_no = etrphone
                )
                contactViewModel.addData(contact)
                contactaptr.notifyDataSetChanged()
                dialog.dismiss()
            }

        }
    }

}