package com.example.renewmate

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge

class InsertDetails :ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.page3)
        val button:Button=findViewById(R.id.addDet)
        button.setOnClickListener{
            val name:EditText=findViewById(R.id.subName)
            val dueDate:EditText=findViewById(R.id.dueDate)
            val cycle:EditText=findViewById(R.id.cycle)
            val db=databaseHandler(this,null)
            db.insert(name.text.toString(),dueDate.text.toString(),cycle.text.toString().toInt())
            val intent=Intent(this,ReadDetails::class.java)
            startActivity(intent)
        }
    }
}