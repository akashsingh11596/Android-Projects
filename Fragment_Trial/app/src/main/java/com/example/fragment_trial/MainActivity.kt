package com.example.fragment_trial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragment_trial.fragments.FragmentA
import com.example.fragment_trial.fragments.FragmentB

class MainActivity : AppCompatActivity(), communicator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //constructor
        val fragmentA = FragmentA()

        //Setting FragmentA to the container
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragmentA).commit()


    }

    override fun passDataCom(editTextInput: String) {
        val bundle = Bundle()
        //              key, editTextInput from above line
        bundle.putString("message", editTextInput)

        //Replacing fragmentA with fragmentB
        val transaction = this.supportFragmentManager.beginTransaction()
        val fragmentB = FragmentB()

        fragmentB.arguments = bundle

        transaction.replace(R.id.fragment_container, fragmentB)
        transaction.commit()
    }
}