package com.example.mydataentryapplication

class Repo {
    private val listofContact =mutableListOf<ContactItem>()

    fun getAllData() = listofContact

    fun addData(contactItem: ContactItem) = listofContact.add(contactItem)
}