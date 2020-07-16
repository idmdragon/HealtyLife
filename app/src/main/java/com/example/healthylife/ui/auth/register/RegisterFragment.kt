package com.example.healthylife.ui.auth.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.healthylife.R
import com.example.healthylife.databinding.FragmentRegisterBinding
import com.example.healthylife.ui.MainActivity
import com.example.healthylife.utils.toast
import com.example.healthylife.utils.viewobject.Resource
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RegisterFragment : Fragment() {
    private lateinit var auth: FirebaseAuth

    private val registerViewModel = RegisterViewModel()

    private lateinit var registerButton: MaterialButton


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        auth = Firebase.auth

        val registerBinding: FragmentRegisterBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_register, container, false
        )

        registerBinding.registerViewModel = registerViewModel

        // View Binding
        setupViewBinding(registerBinding.root)

        // Init Progress Dialog
//        initProgressDialog()

        return registerBinding.root


    }

    private fun setupViewBinding(view: View) {
        registerButton = view.findViewById(R.id.btn_register)

        registerButton.setOnClickListener {
            registerViewModel.registerWithEmailAndPassword()

            registerViewModel.result.observe(viewLifecycleOwner, Observer { task ->
                when (task) {
                    is Resource.Loading -> {
//                        if (!alertDialog.isShowing) alertDialog.show()
                    }

                    is Resource.Success -> {
                        requireContext().toast("Success")
//                        if (alertDialog.isShowing) alertDialog.dismiss()
                        navigateToMain()
                    }

                    is Resource.Failure -> {
//                        if (alertDialog.isShowing) alertDialog.dismiss()
                        requireContext().toast(task.throwable.message.toString())
                    }

                    else -> {
                        // do nothing
                        requireContext().toast(task.toString())
                    }
                }
            })
        }
    }

    private fun navigateToMain() {
        Log.d("LoginDebug", "LOGIN: Navigate to Main")
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
    }

}


