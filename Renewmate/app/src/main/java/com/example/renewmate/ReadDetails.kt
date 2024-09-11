package com.example.renewmate

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.activity.ComponentActivity
import com.example.renewmate.databaseHandler.Companion.cycle
import com.example.renewmate.databaseHandler.Companion.duedate
import com.example.renewmate.databaseHandler.Companion.name
import com.example.renewmate.databaseHandler.Companion.status

class ReadDetails :ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page2)
        val mainLayout:RelativeLayout= findViewById(R.id.buttonContainer)

        val db=databaseHandler(this,null)
        val values = ContentValues();
        val result=db.read()
        result.moveToFirst()
        var i=0
        if (result.moveToFirst()) {
            do {
                values.put(name, result.getString(0))
                values.put(duedate, result.getString(1))
                values.put(status, result.getString(2))
                values.put(cycle, result.getString(3))
                addItem(values.getAsString("name"),values.getAsString("duedate"),values.getAsString(
                    status),values.getAsString(cycle).toInt(),mainLayout,i)
                i+=1
            } while (result.moveToNext())
        }
        result.close()

        val button:Button=findViewById(R.id.addPage)
        button.setOnClickListener{
            val intent=Intent(this,InsertDetails::class.java)
            startActivity(intent)
        }
    }

    fun addItem(name:String,dueDate: String,status:String,cycle:Int,container:RelativeLayout,i:Int) {
        val width=1000
        val height=200
        val margin=10
        val layoutParams = LinearLayout.LayoutParams(width,height)
        layoutParams.setMargins(0,(margin+(i*(margin+height))),0,0)
        val dynamicButton = Button(this)
        dynamicButton.layoutParams = layoutParams
        dynamicButton.text = name+" "+dueDate+" "+status+" Recharge"
        dynamicButton.setBackgroundColor(0xFF00ff00.toInt())
        dynamicButton.setOnClickListener{
            val intent=Intent(this,Description::class.java)
            intent.putExtra("name",name)
            intent.putExtra("date",dueDate)
            intent.putExtra("status",status)
            startActivity(intent)
        }
        container.addView(dynamicButton)
    }
}