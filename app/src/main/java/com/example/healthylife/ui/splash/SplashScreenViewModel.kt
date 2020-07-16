package com.example.healthylife.ui.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.healthylife.utils.Constants
import com.example.healthylife.utils.viewobject.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay

class SplashScreenViewModel : ViewModel() {

    lateinit var authInstance: LiveData<Resource<FirebaseAuth>>

    private val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    init {
        checkAuthInstance()
    }

    private fun checkAuthInstance() {

        // Inisialiasi authInstance
        authInstance = liveData(Dispatchers.IO) {
            try {
                val authResult: Resource<FirebaseAuth> = Resource.Success(mAuth)
                delay(Constants.SPLASH_SCREEN_TIMEOUT)
                emit(authResult)
            } catch (e: FirebaseAuthException) {
                emit(Resource.Failure(e.cause!!))
            }
        }
    }

    fun logout() {
        mAuth.signOut()
    }


}