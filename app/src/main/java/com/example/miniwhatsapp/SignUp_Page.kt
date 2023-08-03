package com.example.miniwhatsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp_Page : AppCompatActivity() {

    private lateinit var login_username: EditText
    private lateinit var login_email: EditText
    private lateinit var login_password: EditText
    private lateinit var signup_button: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var DbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_page)

        supportActionBar?.hide()

        login_username=findViewById(R.id.username)
        login_email=findViewById(R.id.loginemail)
        login_password=findViewById(R.id.loginpassword)
        signup_button=findViewById(R.id.signupbutton)
        mAuth=FirebaseAuth.getInstance()

        signup_button.setOnClickListener {
            val name=login_username.text.toString()
            val email=login_email.text.toString()
            val password=login_password.text.toString()

            signup(name, email, password)
        }
    }

    private fun signup(name: String, email: String, password: String){
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    addUserToDatabase(name, email, mAuth.currentUser?.uid!!)
                    val intent= Intent(this@SignUp_Page, Logine_Page::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this@SignUp_Page,"Error In Signing Up! PLease Try Again!!!", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun addUserToDatabase(name: String,email: String,uid: String)
    {
        DbRef=FirebaseDatabase.getInstance().getReference()
        DbRef.child("user").child(uid).setValue(User(name ,email ,uid))
    }
}