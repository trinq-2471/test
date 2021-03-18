package com.example.testkotlin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var linearLayout : GridLayoutManager = GridLayoutManager(this,2)
        rcv_user.setLayoutManager(linearLayout)

        var mlist = arrayListOf<User>(User(R.drawable.user1,"Tri",25), User(R.drawable.user2,"Nhan", 24), User(R.drawable.user1,"Khoa",20))
        var userAdapter : UserAdapter = UserAdapter(mlist)
        rcv_user.adapter = userAdapter


        btn_add.setOnClickListener(View.OnClickListener {
            mlist.add( User(R.drawable.user1,"Minh",22) )
            userAdapter.setData(mlist)
        })
    }

}