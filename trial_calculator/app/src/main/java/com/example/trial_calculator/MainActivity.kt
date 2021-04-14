package com.example.trial_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trial_calculator.fragments.OperationFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //constructor
        val fragmentA = OperationFragment()

        //Setting FragmentA to the container
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragmentA).commit()
    }
}