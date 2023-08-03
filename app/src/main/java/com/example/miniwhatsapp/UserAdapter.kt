package com.example.miniwhatsapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class UserAdapter(val context: Context, val userlist: ArrayList<User>):
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.user_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current_user= userlist[position]
        holder.showname.text = current_user.name

        holder.itemView.setOnClickListener {
            val intent = Intent(context,ChatPage::class.java)
            intent.putExtra("name", current_user.name)
            intent.putExtra("uid", current_user.uid)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return userlist.size
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val showname=itemView.findViewById<TextView>(R.id.show_name)
    }

}