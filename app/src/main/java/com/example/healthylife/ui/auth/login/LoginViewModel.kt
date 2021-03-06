package com.example.healthylife.ui.auth.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.healthylife.utils.Constants
import com.example.healthylife.utils.CustomException
import com.example.healthylife.utils.viewobject.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await

class LoginViewModel {

    var email: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()

    private val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    lateinit var result: LiveData<Resource<AuthResult?>>

    fun loginWithEmailAndPassword() {
        result = liveData(Dispatchers.IO) {
            try {
                emit(Resource.Loading())
                Log.d("LoginDebug", "${email.value} | ${password.value}")
                if (email.value.isNullOrEmpty() || password.value.isNullOrEmpty()) {
                    emit(Resource.Failure(CustomException("Email or Password can't be blank")))
                } else {
                    Log.d(
                        "LoginDebug",
                        "Email and pass not empty ${email.value} | ${password.value}"
                    )
                    val loginAuthResult: Resource<AuthResult?> = loginWithEmailAndPassword(
                        email = email.value!!,
                        password = password.value!!
                    )
                    emit(loginAuthResult)

                }
            } catch (e: FirebaseAuthException) {
                emit(Resource.Failure(e.cause!!))
            }
        }
    }

    suspend fun loginWithEmailAndPassword(
        email: String,
        password: String
    ): Resource<AuthResult?> {
        return try {
            val data = mAuth
                .signInWithEmailAndPassword(email, password)
                .await()

            Resource.Success(data)
        } catch (e: FirebaseAuthException) {
            Resource.Failure(e)
        }
    }

}


