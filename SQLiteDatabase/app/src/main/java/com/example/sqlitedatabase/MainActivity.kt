package com.example.sqlitedatabase

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

private lateinit var buttonFirst: Button
private lateinit var buttonNext: Button
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editNameViewScreen:EditText? = findViewById(R.id.edName)
        val editMeaningViewScreen:EditText? = findViewById(R.id.edMeaning)
        val buttonFirst: Button? = findViewById(R.id.btFirst)
        val buttonNext: Button? = findViewById(R.id.btNext)
        var helper = MyHelper(applicationContext)//applicationContext is my constructor which will call Context from the MyHelper.kt file
        // db is the instance of SQLiteDatabase
        // rs is the instance of the Cursor class.
        var db = helper.readableDatabase
        //var rs = db.rawQuery("SELECT * FROM ACTABLE WHERE NAME = ?", arrayOf("WWW"))
        // To retrieve all the records uncomment the below line:
        var rs = db.rawQuery("SELECT * FROM ACTABLE", null) // This line will give all the data that is stored in the database.

        buttonFirst?.setOnClickListener {
            if (rs.moveToFirst())
            {
                editNameViewScreen?.setText(rs.getString(1))
                editMeaningViewScreen?.setText(rs.getString(2))
            }
            else
            {
                Toast.makeText(applicationContext,"No Data Found", Toast.LENGTH_SHORT).show()
            }
            buttonNext?.setOnClickListener {
                if (rs.moveToNext())
                {
                    editNameViewScreen?.setText(rs.getString(1))
                    editMeaningViewScreen?.setText(rs.getString(2))
                }
                else if(rs.moveToFirst())
                {
                    editNameViewScreen?.setText(rs.getString(1))
                    editMeaningViewScreen?.setText(rs.getString(2))
                }
            }
        }

    }
}