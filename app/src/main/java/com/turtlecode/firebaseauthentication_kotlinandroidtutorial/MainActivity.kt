package com.turtlecode.firebaseauthentication_kotlinandroidtutorial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {

        auth = FirebaseAuth.getInstance()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val current_user = auth.currentUser
        if (current_user != null) {
            val intent = Intent(this,Photo_Sharing::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun SignIn(view: View) {

        val email = emailText.text.toString()
        val password = passwordText.text.toString()
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val guncelKullanici = auth.currentUser?.email.toString()
                Toast.makeText(this,"Hoşgeldin: ${guncelKullanici}", Toast.LENGTH_LONG).show()

                val intent = Intent(this,
                    Photo_Sharing::class.java)
                startActivity(intent)
                finish()

            }
        }.addOnFailureListener { exception ->
            Toast.makeText(this,exception.localizedMessage, Toast.LENGTH_LONG).show()

        }
    }

    fun Register(view:View) {
        val email = emailText.text.toString()
        val password = passwordText.text.toString()
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val intent = Intent(this,Photo_Sharing::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext,exception.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }



}