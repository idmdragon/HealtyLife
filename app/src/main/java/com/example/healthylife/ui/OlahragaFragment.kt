package com.example.healthylife.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.healthylife.R
import kotlinx.android.synthetic.main.fragment_olahraga.*

class OlahragaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        barbelworkout.setOnClickListener{
            val intent = Intent(this@OlahragaFragment, WorkOutActivity::class.java)
            startActivity(intent)
            finish()
        }
        return inflater.inflate(R.layout.fragment_olahraga, container, false)
    }



}