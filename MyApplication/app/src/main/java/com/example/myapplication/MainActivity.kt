package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var button1: Button
    private lateinit var textView1: TextView
    private lateinit var editView1: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button1 = findViewById(R.id.button)
        textView1 = findViewById(R.id.textView)
        editView1 = findViewById<EditText>(R.id.editView1)

        button1.setOnClickListener {
            var text = editView1.text
            textView1.setText(text)
        }

    }
}