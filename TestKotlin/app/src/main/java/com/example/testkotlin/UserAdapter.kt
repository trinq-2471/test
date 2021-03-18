package com.example.testkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter( private var listUser : ArrayList<User> ) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    // Khai báo Interface giúp cho việc click vào phần tử của recycleview
    interface IClickItemUserListener {
        fun onClickItemUser(user: User?)
    }

    public fun setData(newList : ArrayList<User>) {
        listUser = newList
        notifyDataSetChanged()
    }

    public inner class UserViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView),View.OnClickListener
    {
        var imgUserAvatar: ImageView
        var tvUserName: TextView
        var tvUserAge: TextView
        lateinit var iClickItemUserListener : IClickItemUserListener

        init {
            imgUserAvatar = itemView.findViewById(R.id.imgUserAvatar)
            tvUserName = itemView.findViewById(R.id.tvUserName)
            tvUserAge = itemView.findViewById(R.id.tvUserAge)
        }

        public fun setItemClickListener( iClickItemUserListener: IClickItemUserListener ){
            this.iClickItemUserListener = iClickItemUserListener
        }

        override fun onClick(v: View?) {
            this.iClickItemUserListener.onClickItemUser(listUser.get(adapterPosition))
        }
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

        var i : IClickItemUserListener
//        holder.setItemClickListener( )
    }

    override fun getItemCount(): Int {
        if (listUser != null){
            return listUser.size
        }
        return 0
    }
}