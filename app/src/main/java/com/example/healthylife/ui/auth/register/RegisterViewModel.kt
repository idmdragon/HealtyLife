package com.example.healthylife.ui.auth.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.healthylife.model.User
import com.example.healthylife.utils.CustomException
import com.example.healthylife.utils.viewobject.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await

class RegisterViewModel : ViewModel() {

    var name: MutableLiveData<String> = MutableLiveData()
    var email: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()

    private val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    private val mRef: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    lateinit var result: LiveData<Resource<AuthResult?>>

    fun registerWithEmailAndPassword() {
        result = liveData(Dispatchers.IO) {
            try {
                emit(Resource.Loading())
                if (email.value.isNullOrEmpty() || password.value.isNullOrEmpty()) {
                    emit(Resource.Failure(CustomException("Email or Password can't be blank")))
                } else {
                    val registerAuthResult: Resource<AuthResult?> = registerWithEmailAndPassword(
                        name = name.value!!,
                        email = email.value!!,
                        password = password.value!!
                    )
                    emit(registerAuthResult)

                }
            } catch (e: FirebaseAuthException) {
                emit(Resource.Failure(e.cause!!))
            }
        }
    }


    private suspend fun registerWithEmailAndPassword(
        name: String,
        email: String,
        password: String
    ): Resource<AuthResult?> {
        return try {
            val data = mAuth
                .createUserWithEmailAndPassword(email, password)
                .await()

            insertUserData(name, email)

            Resource.Success(data)
        } catch (e: FirebaseAuthException) {
            Resource.Failure(e)
        }
    }

    private fun insertUserData(name: String, email: String) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        val userRef: DocumentReference = mRef.collection("users").document(userId.toString())
        val userModel = User(name, email)
        return try {
            val data = userRef.set(userModel)
        } catch (e: FirebaseFirestoreException) {

        }
    }

}