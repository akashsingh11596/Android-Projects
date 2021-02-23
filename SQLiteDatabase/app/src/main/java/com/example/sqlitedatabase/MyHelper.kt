package com.example.sqlitedatabase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//MyHelper class having the constructor in which we are declaring the tag Context.
//Meaning we are creating implementing three tier architecture. Suppose we have multiple
//activity and we want to move from one activity to any activity and want to access the HELPER class, then
//we should be able to do so.

//Press Ctrl+P inside the bracket of SQLiteOpenHelper and it will show what enteries we need to make.
class MyHelper(context: Context) : SQLiteOpenHelper(context, "ACDB", null, 1)//ACDN(name of database) meaning Acronym DB
{
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE ACTABLE(_id integer primary key autoincrement, NAME TEXT, MEANING TEXT)")
        db?.execSQL("INSERT INTO ACTABLE(NAME, MEANING)VALUES('WWW', 'WORLD WIDE WEB')")
        db?.execSQL("INSERT INTO ACTABLE(NAME, MEANING)VALUES('GDG', 'GOOGLE DEVELOPER GROUP')")
        db?.execSQL("INSERT INTO ACTABLE(NAME, MEANING)VALUES('AVD', 'ANDROID VIRTUAL DEVICE')")
        db?.execSQL("INSERT INTO ACTABLE(NAME, MEANING)VALUES('A', 'AKASH')")

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

}