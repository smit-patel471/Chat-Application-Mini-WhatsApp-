package com.example.miniwhatsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    private lateinit var usersRecyclerView: RecyclerView
    private lateinit var userList: ArrayList<User>
    private lateinit var adapter: UserAdapter
    private lateinit var mAuth: FirebaseAuth
    private lateinit var DbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userList= ArrayList()
        adapter= UserAdapter(this,userList)
        mAuth= FirebaseAuth.getInstance()
        usersRecyclerView=findViewById(R.id.UsersRecyclerView)
        usersRecyclerView.layoutManager=LinearLayoutManager(this)
        usersRecyclerView.adapter=adapter
        DbRef=FirebaseDatabase.getInstance().getReference()

        DbRef.child("user").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear() //to clear the previous list
                for(postSnapshot in snapshot.children) {
                    val currentuser = postSnapshot.getValue(User::class.java)
                    if(mAuth.currentUser?.uid != currentuser?.uid) { //to not show the current user
                        userList.add(currentuser!!)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.logout) {
            mAuth.signOut()
            val intent = Intent(this@MainActivity, Logine_Page::class.java)
            finish()
            startActivity(intent)
            return true
        }
        return true
    }
}