package com.example.renewmate

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page1)
        val button:Button=findViewById(R.id.Start)
        button.setOnClickListener{
            val intent=Intent(this,ReadDetails::class.java)
            startActivity(intent)
        }
    }
}