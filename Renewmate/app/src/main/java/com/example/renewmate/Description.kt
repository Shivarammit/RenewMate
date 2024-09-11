package com.example.renewmate

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class Description:ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page4)
        val name:TextView=findViewById(R.id.subName)
        val dueDate:TextView=findViewById(R.id.subDueDate)
        val status:Button=findViewById(R.id.status)
        val recharge:Button=findViewById(R.id.recharge)
        name.text=intent.getStringExtra("name")
        dueDate.text=intent.getStringExtra("date")
        status.text=intent.getStringExtra("status")

        status.setOnClickListener{
            val db=databaseHandler(this,null)
            if (status.text.toString()=="Not Paid"){
                status.text="Paid"
                db.update("Paid",name.text.toString())
            }else{
                status.text="Not Paid"
                db.update("Not Paid",name.text.toString())
            }
        }

        recharge.setOnClickListener{
            val intent=Intent(Intent.ACTION_VIEW, Uri.parse("https://"+name.text.toString()+".com"))
            startActivity(intent)
        }
    }
}