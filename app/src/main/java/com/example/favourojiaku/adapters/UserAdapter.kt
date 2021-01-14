package com.example.favourojiaku.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.favourojiaku.R
import com.example.favourojiaku.models.Users
import kotlinx.android.synthetic.main.user_holder.view.*

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserHolder>() {

    private var _user: List<Users> = listOf()
    val user : List<Users>
        get() = _user
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_holder, parent, false)
        return UserHolder(view)
    }

    override fun getItemCount(): Int{
        return _user.size
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(_user[position])
    }
    fun setUser(user: List<Users>) {
        this._user = user
        notifyDataSetChanged()
    }

    inner class UserHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(user: Users){
            itemView.fullname.text = user.fullName
            itemView.gender.text = user.gender
            itemView.createdAt.text = user.createdAt
            //itemView.avatar.te
        }
    }
}