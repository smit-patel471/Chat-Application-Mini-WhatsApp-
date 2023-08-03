package com.example.miniwhatsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Logine_Page : AppCompatActivity() {

    private lateinit var login_email: EditText
    private lateinit var login_password: EditText
    private lateinit var login_button: Button
    private lateinit var signup_button: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logine_page)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        login_email=findViewById(R.id.loginemail)
        login_password=findViewById(R.id.loginpassword)
        login_button=findViewById(R.id.loginbutton)
        signup_button=findViewById(R.id.signupbutton)

        signup_button.setOnClickListener {
            val intent=Intent(this, SignUp_Page::class.java)
            startActivity(intent)
        }

        login_button.setOnClickListener {
            val email=login_email.text.toString()
            val password=login_password.text.toString()

            login(email, password)
        }
    }

    private fun login(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent=Intent(this@Logine_Page, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this@Logine_Page, "User Not Found! Please SignUp First!!!", Toast.LENGTH_SHORT).show()
                }
            }
    }
}