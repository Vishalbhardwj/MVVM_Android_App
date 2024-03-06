package com.example.mydataentryapplication

import android.net.Uri

data class ContactItem(
    val image:Uri,
    val name:String,
    val phone_no:String
)