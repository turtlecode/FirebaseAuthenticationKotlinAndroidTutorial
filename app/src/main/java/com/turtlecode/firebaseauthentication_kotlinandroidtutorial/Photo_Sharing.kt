package com.turtlecode.firebaseauthentication_kotlinandroidtutorial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_photo_sharing.*

class Photo_Sharing : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_sharing)
        auth = FirebaseAuth.getInstance()

        textView.text = "Welcome ${auth.currentUser!!.email}"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.option,menu)
        return super.onCreateOptionsMenu(menu)
    }

    // menude obje secildiğinde ne yapılacak
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.SignOut) {
            auth.signOut()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}