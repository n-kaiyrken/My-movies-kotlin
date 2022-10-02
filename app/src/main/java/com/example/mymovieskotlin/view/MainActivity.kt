package com.example.mymovieskotlin.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.mymovieskotlin.data.User
import com.example.mymovieskotlin.databinding.ActivityMainBinding
import com.example.mymovieskotlin.viewmodel.MainActivityViewModel
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    )
    { res -> this.onSignInResult(res) }

    private lateinit var binding: ActivityMainBinding
    private var mainActivityViewModel = MainActivityViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        openRegistrationScreen()
    }

    /**
     * We make a call to firebase auth api to show dialog for registration
     */
    fun openRegistrationScreen(){
        val intentToAnotherScreen = Intent(this, MoviesActivity::class.java)
        startActivity(intentToAnotherScreen)

        val providers = arrayListOf(AuthUI.IdpConfig.EmailBuilder().build())

        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        signInLauncher.launch(signInIntent)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        if (result.resultCode == RESULT_OK) {
            Log.d("Check", "Successfully signed in")
            val authUser = FirebaseAuth.getInstance().currentUser
            authUser?.let {
                val email = it.email.toString()
                val uid = it.uid
                mainActivityViewModel.updateUserData(uid, User(email,uid))
            }
        } else {
            Toast.makeText(this, "Authorisation error!", Toast.LENGTH_SHORT)
        }
    }
}