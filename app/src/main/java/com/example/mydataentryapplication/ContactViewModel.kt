package com.example.mydataentryapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContactViewModel(
     private val repo: Repo
):ViewModel() {

    val liveDataContacts =MutableLiveData<List<ContactItem>>()

    init {
        getData()
    }

    fun getData( ){
        val data =repo.getAllData()
        liveDataContacts.value =data
    }

    fun addData(contactItem: ContactItem){
        repo.addData(contactItem)

    }
}