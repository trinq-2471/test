package com.example.testkotlin

import android.content.Context
import android.graphics.Region
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.testkotlin.extension.setTextOrHide
import com.example.testkotlin.extension.setToastMessage
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnTestShowToast.setToastMessage(this,"test extention")

        btnCheckText.setOnClickListener(View.OnClickListener {
            tvTest.setTextOrHide(edtTest.text.toString())
        })

    }
}