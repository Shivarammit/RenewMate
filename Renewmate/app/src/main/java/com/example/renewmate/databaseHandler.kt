package com.example.renewmate

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper

class databaseHandler(context: Context,factory: CursorFactory?):SQLiteOpenHelper(context,DATABASE,factory,DATABASEVER) {
    override fun onCreate(db: SQLiteDatabase){
        val statement="CREATE TABLE ${TABLE}(name TEXT, duedate DATE,status TEXT,cycle INTEGER)"
        db.execSQL(statement)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        //onCreate(db)
    }

    fun insert(subName:String,subDue:String,subCycle:Int){
        val db=this.writableDatabase
        var values=ContentValues()
        values.put(name,subName)
        values.put(duedate,subDue)
        values.put(cycle, subCycle)
        values.put(status, "NOT PAID")
        db.insert(TABLE,null,values)
        db.close()
    }

    fun update(subStatus:String,subName:String){
        val db=this.writableDatabase
        db.execSQL("UPDATE ${TABLE} SET ${status}='${subStatus}' where ${name}='${subName}'")
    }

    fun read(): Cursor{
        val db = this.readableDatabase;
        val result = db.rawQuery("select * from ${TABLE}", null)
        return result;
    }

    companion object{
        private val DATABASE="remainders"
        private val TABLE="subscriptions"
        private val DATABASEVER=1
        var name="name";
        var duedate="duedate"
        var status="status"
        var cycle="cycle"
    }
}