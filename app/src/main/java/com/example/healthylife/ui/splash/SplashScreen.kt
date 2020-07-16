package com.example.healthylife.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.healthylife.ui.AuthActivity
import com.example.healthylife.R
import com.example.healthylife.utils.viewobject.Resource

class SplashScreen : AppCompatActivity() {

    private val splahScreenViewModel: SplashScreenViewModel = SplashScreenViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        splahScreenViewModel.authInstance.observe(this, Observer { mAuth ->
            when (mAuth) {
                is Resource.Success -> if (mAuth.data.currentUser != null) {
                    navigateToMain()
                } else {
                    navigateToAuth()
                }

//                    is Resource.Failure -> {
//                        Thread.sleep(Constants.SPLASH_SCREEN_DELAY)
//                        navigateToMainActivity()
//                    }
            }

        })
    }

    private fun navigateToMain() {
        Log.d("SplashScreenDebug", "SPLASH: Navigate to Main")
        splahScreenViewModel.logout()
    }

    private fun navigateToAuth() {
        val intent = Intent(this@SplashScreen, AuthActivity::class.java)
        startActivity(intent)
        finish()

        Log.d("SplashScreenDebug", "SPLASH: Navigate to Auth")
    }
}
