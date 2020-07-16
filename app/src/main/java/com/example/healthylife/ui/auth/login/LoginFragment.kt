package com.example.healthylife.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.healthylife.R
import com.example.healthylife.databinding.FragmentLoginBinding
import com.example.healthylife.ui.MainActivity
import com.example.healthylife.utils.toast
import com.example.healthylife.utils.viewobject.Resource
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment : Fragment() {

    // Utils
    private lateinit var alertDialog: AlertDialog

    private lateinit var auth: FirebaseAuth

    // Views
    private lateinit var loginButton: MaterialButton

    // View Model
    private val loginViewModel: LoginViewModel = LoginViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        auth = Firebase.auth

        val loginbinding:FragmentLoginBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_login,container,false)

        loginbinding.loginViewModel = loginViewModel

        // View Binding
        setupViewBinding(loginbinding.root)

        // Init Progress Dialog
        initProgressDialog()

        return loginbinding.root
    }

    private fun setupViewBinding(view: View) {
        loginButton = view.findViewById(R.id.btn_login)

        loginButton.setOnClickListener {
            loginViewModel.loginWithEmailAndPassword()

            // Observe
            loginViewModel.result.observe(viewLifecycleOwner, Observer { task ->
                when (task) {
                    is Resource.Loading -> {
                        if (!alertDialog.isShowing) alertDialog.show()
                    }

                    is Resource.Success -> {
                        requireContext().toast("Success")
                        if (alertDialog.isShowing) alertDialog.dismiss()
                        navigateToMain()
                    }

                    is Resource.Failure -> {
                        if (alertDialog.isShowing) alertDialog.dismiss()
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

    private fun initProgressDialog() {
        val dialogBuilder = AlertDialog.Builder(requireContext())
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.progress_dialog, null)
        dialogBuilder.setView(dialogView)
        dialogBuilder.setCancelable(false)
        alertDialog = dialogBuilder.create()
    }


}