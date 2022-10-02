package com.example.mymovieskotlin.model.repository

import com.example.mymovieskotlin.data.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseRepositoryImpl: FirebaseRepository {
    private var database = Firebase.database.reference

    override fun updateDB(uid: String, firebaseUser: User){
        database.child("users").child(uid).setValue(firebaseUser)
    }
}