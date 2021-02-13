package com.example.activitycycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate()called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart()
    {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }
    override fun onResume()
    {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }
    override fun onPause()
    {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }
    override fun onStop()
    {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }
    override fun onDestroy()
    {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }
}