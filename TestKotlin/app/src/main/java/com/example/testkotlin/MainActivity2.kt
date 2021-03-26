package com.example.testkotlin

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {

    private lateinit var listUser : ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val linearLayout : LinearLayoutManager = LinearLayoutManager(this)
        rcv_user.setLayoutManager(linearLayout)

        listUser = arrayListOf<User>( User(R.drawable.user1,"User 1",25)
                                    , User(R.drawable.user2,"User 2", 24)
                                    , User(R.drawable.user1,"User 3",20))

        val userAdapter : UserAdapter = UserAdapter(listUser, ::userOnClick)
        rcv_user.adapter = userAdapter

        btn_add.setOnClickListener(View.OnClickListener {
            listUser.add( User(R.drawable.user1,"New User",22) )
            userAdapter.setData(listUser)
        })
    }

    fun userOnClick(user: User) {
        Toast.makeText(this, "name : ${user.name}, age ${user.age}", Toast.LENGTH_SHORT).show()
    }

//    override fun onUserClick(user: User) {
//        Toast.makeText(this, "name : ${user.name}, age ${user.age}", Toast.LENGTH_SHORT).show()
//    }
}