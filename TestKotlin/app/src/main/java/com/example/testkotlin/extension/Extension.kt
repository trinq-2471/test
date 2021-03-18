package com.example.testkotlin.extension

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


// Sets a text to TextView or hides the TextView if the text is null or empty
fun TextView.setTextOrHide(string: String){
    return if (string.isNullOrEmpty() || string.isNullOrBlank()) {
        this.visibility = View.GONE
    } else {
        this.visibility = View.VISIBLE
        this.text = string
    }
}

// Show toast message in center screen when click button
fun Button.setToastMessage(context: Context, message : String ){
    this.setOnClickListener{
        var toast = Toast.makeText(context,message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show()
    }
}