package com.example.fragment_trial.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.fragment_trial.R
import com.example.fragment_trial.communicator
import kotlinx.android.synthetic.main.fragment_a.view.*

class FragmentA : Fragment() {
   // private lateinit var sendBTN:Button

    private lateinit var communicator: communicator//Referring variable to the interface

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view  = inflater.inflate(R.layout.fragment_a, container, false)

        communicator = activity as communicator
        //sendBTN = view.findViewById(R.id.sensendBTN)
        view.sendBTN.setOnClickListener{
            communicator.passDataCom(view.messageInput.text.toString())

        }
        return view
    }

}