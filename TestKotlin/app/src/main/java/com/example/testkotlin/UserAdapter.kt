package com.example.testkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class UserAdapter( private var listUser : ArrayList<User>,
                   private var clicklistner : (User) -> Unit) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    public fun setData(newList : ArrayList<User>) {
        listUser = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        var user : User = listUser.get(position)
        holder.imgUserAvatar.setImageResource(user.imgReouser)
        holder.tvUserName.setText(user.name)
        holder.tvUserAge.setText(user.age.toString())
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener
    {
        var imgUserAvatar: ImageView = itemView.findViewById(R.id.imgUserAvatar)
        var tvUserName: TextView = itemView.findViewById(R.id.tvUserName)
        var tvUserAge: TextView= itemView.findViewById(R.id.tvUserAge)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (adapterPosition != RecyclerView.NO_POSITION){
                val user = listUser.get(adapterPosition)
                clicklistner(user)
//                clicklistner.onUserClick(user)
            }
        }
    }

//    interface IClickUserListener{
//        fun onUserClick(user: User)
//    }

    override fun getItemCount(): Int {
        if (listUser != null){
            return listUser.size
        }
        return 0
    }
}